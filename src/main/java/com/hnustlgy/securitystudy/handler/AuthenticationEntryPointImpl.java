package com.hnustlgy.securitystudy.handler;

import com.alibaba.fastjson.JSON;
import com.hnustlgy.securitystudy.utils.R;
import com.hnustlgy.securitystudy.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //处理认证异常
        WebUtils.renderString(response, JSON.toJSONString(R.error("用户认证失败")));
    }
}
