package com.example.infusion.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterReq {

    //手机号
    private String phone;
    //验证码
    private String code;
    //昵称
    private String username;
    //密码
    private String password;
    //确认密码
    private String confirmPassword;

}
