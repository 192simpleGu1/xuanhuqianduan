package com.example.infusion.common.config;

import com.example.infusion.common.Interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    //自定义的拦截器对象
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //注册自定义拦截器对象
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")//设置拦截器拦截的请求路径（ /** 表示拦截所有请求）
                .excludePathPatterns("/infusion/login",
                        "/infusion/register",
                        "/infusion/send_code/login",
                        "/infusion/send_code/register",
                        "/infusion/send_code/forget_password",
                        "/infusion/forget_password",
                        "/infusion/loginByCode");//设置不拦截的请求路径
    }
}