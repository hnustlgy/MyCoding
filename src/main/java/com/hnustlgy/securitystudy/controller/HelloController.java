package com.hnustlgy.securitystudy.controller;

import com.hnustlgy.securitystudy.expression.MyExpressionRoot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {
    @RequestMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('admin')")
    @PreAuthorize("@myExpressionRoot.hasAuthorize('admin')")//自定义权限判断
    public String login(){
        return "hello";
    }
}
