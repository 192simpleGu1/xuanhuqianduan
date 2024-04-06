package com.example.infusion.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.infusion.common.enums.BaseErrorCode;
import com.example.infusion.common.enums.CustomizeCode;
import com.example.infusion.common.error.ServiceException;
import com.example.infusion.common.utils.*;
import com.example.infusion.domain.Balance;
import com.example.infusion.dto.req.*;
import com.example.infusion.dto.resp.*;
import com.example.infusion.mapper.BalanceMapper;
import com.example.infusion.mapper.UserMapper;
import com.example.infusion.domain.User;
import com.example.infusion.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author admin
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-03-14 23:26:07
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Value("user.password.salt")
    private String salt;

    @Value("login.token")
    private String login_key;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BalanceMapper balanceMapper;
    @Override
    public LoginResp login(LoginReq loginReq) {
        //检查 用户名 和 密码 是否存在
        if (StringUtils.isEmpty(loginReq.getPhone())) {
            throw new ServiceException(CustomizeCode.USER_NOT_NULL);
        }
        if(StringUtils.isEmpty(loginReq.getPassword())){
            throw new ServiceException(CustomizeCode.PASSWORD_NOT_NULL);
        }
        Wrapper<User> user = Wrappers.lambdaQuery(User.class)
                .eq(User::getPhone,loginReq.getPhone())
                .eq(User::getPassword,MD5Utils.MD5Upper(loginReq.getPassword(),salt));
        List<User> users = userMapper.selectList(user);

        if(users.size()==0){
            throw new ServiceException(CustomizeCode.USER_LOGIN_ERROR);
        }
        if (users.size()>1) {
            throw new ServiceException(BaseErrorCode.CLIENT_ERROR);
        }
        //将手机号存入redis
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone",loginReq.getPhone());
        String token = JWTUtil.createToken(map,login_key.getBytes());
        redisTemplate.opsForHash().put("login:"+loginReq.getPhone(),"token",token.getBytes());
        redisTemplate.expire("login:"+loginReq.getPhone(),31,TimeUnit.DAYS);

        return LoginResp.builder().message("登陆成功").token(token).build();
    }



    @Transactional
    @Override
    public RegisterResp register(RegisterReq registerReq) {

        Wrapper<User> eq = Wrappers.lambdaQuery(User.class)
                .eq(User::getPhone, registerReq.getPhone());
        User user = userMapper.selectOne(eq);
        //手机号校验正确性
        if (!CheckPhoneHelper.validator(registerReq.getPhone())) {
            throw new ServiceException(CustomizeCode.PHONE_NOT_CONFIRM);
        }
        //手机号已存在
        if(user!=null) throw new ServiceException(CustomizeCode.PHONE_EXIST_ERROR);
        //密码校验
        if(!registerReq.getPassword().equals(registerReq.getConfirmPassword())){
            throw new ServiceException(CustomizeCode.PASSWORD_VERIFY_ERROR);
        }
        User build = User.builder()
                .username(registerReq.getUsername())
                .password(MD5Utils.MD5Upper(registerReq.getPassword(), salt))
                .phone(registerReq.getPhone())
                .build();

        //通过手机号查找验证码并校验
        if(!redisTemplate.opsForValue().get(registerReq.getPhone()+":"+"register").equals(registerReq.getCode())){
            throw new ServiceException(CustomizeCode.PHONE_VERIFY_ERROR);
        }
        //插入数据
        int insert = userMapper.insert(build);

        //插入余额，初始为0
        int insert1 = balanceMapper.insert(Balance.builder()
                .phone(registerReq.getPhone())
                .balance(0.00).build());
        if(insert1==0) throw new ServiceException(CustomizeCode.BALANCE_INIT_ERROR);
        //没有插入数据，则报插入错误
        if(insert==0) throw new ServiceException(CustomizeCode.REGISTER_ERROR);
        return RegisterResp.builder().message("注册成功").build();
    }

    @Override
    public SendCodeResp sendCode(HttpServletRequest request,SendCodeReq sendCodeReq) {
        String path = request.getRequestURL().toString();

        //截取url最后一个/后的字符串
        String pathAfterLastSlash = path.substring(path.lastIndexOf("/") + 1);
        if(StringUtils.isEmpty(String.valueOf(redisTemplate.opsForValue().get(sendCodeReq.getPhone()+":"+pathAfterLastSlash)))){
            throw new ServiceException(CustomizeCode.PHONE_VERIFY_ERROR);
        }
        String code = VerificationCodeGenerator.generateCode();
        //发送验证码
        log.info("您的验证码是："+code+" 请即时使用，将于5分钟之后过期");
        //保存到redis并设置过期时间为5分钟
        redisTemplate.opsForValue().set(sendCodeReq.getPhone()+":"+pathAfterLastSlash,code,5, TimeUnit.MINUTES);

        return SendCodeResp.builder().code(code).build();
    }

    @Override
    public ForgetPasswordResp forgetPassword(ForgetPasswordReq forgetPasswordReq) {
        //检查code
        if (!redisTemplate.opsForValue().get(forgetPasswordReq.getPhone()+":"+"forget_password").equals(forgetPasswordReq.getCode())) {
            throw new ServiceException(CustomizeCode.PHONE_VERIFY_ERROR);
        }

        //修改密码
        int success_count = userMapper.updateByPhone(MD5Utils.MD5Upper(forgetPasswordReq.getPassword(),salt), forgetPasswordReq.getPhone());
        //修改失败
        if(success_count<=0) throw new ServiceException(CustomizeCode.Phone_NOT_EXIST_ERROR);

        return ForgetPasswordResp.builder().message("修改成功").build();
    }

    @Override
    public LoginByCodeResp loginByCode(LoginByCodeReq loginReq) {
        //检查 手机号 和 验证码 是否存在
        if (StringUtils.isEmpty(loginReq.getPhone())) {
            throw new ServiceException(CustomizeCode.PHONE_NOT_NULL);
        }
        if(StringUtils.isEmpty(loginReq.getCode())){
            throw new ServiceException(CustomizeCode.CODE_NOT_NULL);
        }

        //查用户判断是否存在
        User user = userMapper.selectOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getPhone, MD5Utils.MD5Upper(loginReq.getPhone(), salt)));
        if(StringUtils.isEmpty(String.valueOf(user))){
            //手机号未注册
            throw new ServiceException(CustomizeCode.PHONE_NOT_REGISTER);
        }
        //用户存在
        //判断验证码是否正确
        if (!redisTemplate.opsForValue().get(loginReq.getPhone()+":"+"login").equals(loginReq.getCode())) {
            throw new ServiceException(CustomizeCode.CODE_IS_ERROR);
        }

        //将手机号存入redis
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone",loginReq.getPhone());
        String token = JWTUtil.createToken(map,login_key.getBytes());
        redisTemplate.opsForHash().put("login:"+loginReq.getPhone(),"token",token.getBytes());
        redisTemplate.expire("login:"+loginReq.getPhone(),31,TimeUnit.DAYS);

        return LoginByCodeResp.builder().message("登陆成功").token(token).build();
    }


}




