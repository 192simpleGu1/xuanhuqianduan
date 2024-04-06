package com.example.infusion.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgetPasswordReq {
    //手机号
    String phone;
    //验证码
    String code;
    //新密码
    String password;
}