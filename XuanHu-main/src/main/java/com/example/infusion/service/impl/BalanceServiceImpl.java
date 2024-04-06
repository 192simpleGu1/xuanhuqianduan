package com.example.infusion.service.impl;

import cn.hutool.http.cookie.ThreadLocalCookieStore;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.infusion.common.enums.CustomizeCode;
import com.example.infusion.common.error.ServiceException;
import com.example.infusion.domain.Balance;
import com.example.infusion.domain.User;
import com.example.infusion.dto.resp.BalanceRenewResp;
import com.example.infusion.mapper.UserMapper;
import com.example.infusion.service.BalanceService;
import com.example.infusion.mapper.BalanceMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【balance】的数据库操作Service实现
* @createDate 2024-03-15 13:44:39
*/
@Service
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance>
    implements BalanceService{
    UserMapper userMapper;


}




