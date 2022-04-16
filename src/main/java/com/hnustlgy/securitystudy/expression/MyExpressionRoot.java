package com.hnustlgy.securitystudy.expression;

import com.hnustlgy.securitystudy.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

//自定义权限判断
@Component
public class MyExpressionRoot {

    public boolean hasAuthorize(String authorize){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        List<String> list = loginUser.getPermission();
        return list.contains(authorize);
    }
}
