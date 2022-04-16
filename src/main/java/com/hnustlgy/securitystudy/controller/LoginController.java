package com.hnustlgy.securitystudy.controller;

import com.hnustlgy.securitystudy.entity.User;
import com.hnustlgy.securitystudy.service.LoginService;
import com.hnustlgy.securitystudy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public R<String> login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/logout")
    public R<String> logout(){
        return loginService.logout();
    }
}
