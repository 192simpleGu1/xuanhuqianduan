package com.example.infusion.service;

import com.example.infusion.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.infusion.dto.req.*;
import com.example.infusion.dto.resp.*;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author admin
* @description 针对表【user】的数据库操作Service
* @createDate 2024-03-14 23:26:07
*/
public interface UserService extends IService<User> {

    //登陆
   LoginResp login(LoginReq loginReq);

   RegisterResp register(RegisterReq registerReq);

   SendCodeResp sendCode(HttpServletRequest request,SendCodeReq sendCodeReq);


    ForgetPasswordResp forgetPassword(ForgetPasswordReq forgetPasswordReq);

    LoginByCodeResp loginByCode(LoginByCodeReq loginReq);
}
