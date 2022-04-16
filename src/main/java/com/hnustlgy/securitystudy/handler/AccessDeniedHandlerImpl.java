package com.hnustlgy.securitystudy.handler;

import com.alibaba.fastjson.JSON;
import com.hnustlgy.securitystudy.utils.R;
import com.hnustlgy.securitystudy.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //处理权限异常
        WebUtils.renderString(response, JSON.toJSONString(R.error("权限不足，无法访问")));
    }
}
