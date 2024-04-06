package com.example.infusion.common.enums;

import com.example.infusion.common.error.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public enum CustomizeCode implements IErrorCode {
    USER_LOGIN_ERROR("A000100", "用户或密码不正确"),
    USER_REGISTER_ERROR("A000101", "用户注册失败"),
    USER_NAME_EXIST_ERROR("A000111", "用户名已存在"),
    PHONE_EXIST_ERROR("A000113","手机号已存在"),
    PASSWORD_VERIFY_ERROR("A0000114","密码校验错误"),
    USER_NAME_SENSITIVE_ERROR("A000112", "用户名包含敏感词"),
    USER_NAME_VERIFY_ERROR("A000110", "用户名校验失败"),
    REGISTER_ERROR("A000115","注册失败"),
    PHONE_VERIFY_ERROR("A000116","验证码不存在"),
    PHONE_NOT_CONFIRM("A000117","手机号不正确"),
    USER_NAME_SPECIAL_CHARACTER_ERROR("A000113", "用户名包含特殊字符"),
    BALANCE_INIT_ERROR("A00018", "用户余额初始化失败"),
    Phone_NOT_EXIST_ERROR("A00019", "用户不存在"),

    USER_NOT_LOGIN("A00020","用户未登录"),
    PHONE_NOT_NULL("A00021","手机号不能为空"),
    CODE_NOT_NULL("A00022","验证码不能为空"),
    PHONE_NOT_REGISTER("A00023", "手机号未注册"),
    USER_NOT_NULL("A00024","用户不能为空"),
    PASSWORD_NOT_NULL("A00025","密码不能为空"),
    CODE_IS_ERROR("A00026", "验证码错误"),
    TOKEN_ERROR("S0001","token错误"),
    USER_NOT_EXIST("A00027", "用户不存在"),
    BALANCE_RENEW_ERROR("A00028", "用户续费失败"),
    BALANCE_NOT_ENOUGH("A00029", "余额不足"),
    BED_IS_BIND("A00030", "床位已被绑定"),
    EQUIPMENT_IS_BIND("A00031", "设备已被绑定"),
    BED_BIND_EQUIPMENT("A00032", "请先解绑设备"),
    EQUIPMENT_REFUND_ERROR("A00033", "退款失败");

    private String code;
    private String message;

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    public enum REGISTER_ERROR {}
}
