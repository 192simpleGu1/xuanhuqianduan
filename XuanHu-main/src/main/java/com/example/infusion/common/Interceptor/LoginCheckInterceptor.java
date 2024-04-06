package com.example.infusion.common.Interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONObjectIter;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.infusion.common.convention.Result;
import com.example.infusion.common.convention.Results;
import com.example.infusion.common.enums.CustomizeCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//自定义拦截器
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Value("login.token")
    private String login_key;
    //目标资源方法执行前执行。 返回true：放行    返回false：不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle .... ");
        //1.获取请求url
        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行

        //3.获取请求头中的令牌（token）
        String authToken = request.getHeader("Authorization");
        String token = authToken.substring("Bearer".length() + 1).trim();
        log.info("从请求头中获取的令牌：{}",token);
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set(token);

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(token)){
            log.info("Token不存在");

            //创建响应结果对象
            Result responseResult = Results.failure(CustomizeCode.USER_NOT_LOGIN.code(), CustomizeCode.USER_NOT_LOGIN.message());
            //把Result对象转换为JSON格式字符串 (hutool的用于实现对象和json的转换工具类)
            String json = JSONUtil.toJsonStr(responseResult);
            //设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);

            return false;//不放行
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JWTUtil.verify(token, login_key.getBytes());
            request.setAttribute("phone",JWTUtil.parseToken(token).getPayload("phone"));

        }catch (Exception e){
            log.info("令牌解析失败!");

            //创建响应结果对象
            Result responseResult = Results.failure(CustomizeCode.USER_NOT_LOGIN.code(), CustomizeCode.USER_NOT_LOGIN.message());
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONUtil.toJsonStr(responseResult);
            //设置响应头
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);

            return false;
        }

        //6.放行
        return true;
    }
 

}