package com.example.infusion.controller;


import com.example.infusion.common.convention.Result;
import com.example.infusion.common.convention.Results;
import com.example.infusion.dto.req.*;
import com.example.infusion.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    //用户名和密码登录接口
    @RequestMapping("/infusion/login")
    public Result login(LoginReq loginReq){
        return Results.success(userService.login(loginReq));
    }

    //手机号验证码登录
    @RequestMapping("/infusion/loginByCode")
    public Result loginByCode(@RequestBody LoginByCodeReq loginReq){
        return Results.success(userService.loginByCode(loginReq));
    }

    //注册接口
    @RequestMapping("/infusion/register")
    public Result register(RegisterReq registerReq){
        return Results.success(userService.register(registerReq));
    }

    //登录发送验证码
    @RequestMapping("/infusion/send_code/login")
    public Result sendCode(HttpServletRequest request,SendCodeReq sendCodeReq){
        return Results.success(userService.sendCode(request,sendCodeReq));
    }

    //注册发送验证码
    @RequestMapping("/infusion/send_code/register")
    public Result sendCodeRegister(HttpServletRequest request,SendCodeReq sendCodeReq){
        return Results.success(userService.sendCode(request,sendCodeReq));
    }


    //忘记密码发送验证码
    @RequestMapping("/infusion/send_code/forget_password")
    public Result forgetPasswordSendCode(HttpServletRequest request,SendCodeReq sendCodeReq){
        return Results.success(userService.sendCode(request,sendCodeReq));
    }


    //忘记密码
    @RequestMapping("/infusion/forget_password")
    public Result forgetPassword(ForgetPasswordReq forgetPasswordReq){
        userService.forgetPassword(forgetPasswordReq);
        return Results.success();
    }
}

