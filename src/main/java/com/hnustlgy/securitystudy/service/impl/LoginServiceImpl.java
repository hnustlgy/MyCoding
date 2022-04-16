package com.hnustlgy.securitystudy.service.impl;

import com.hnustlgy.securitystudy.entity.LoginUser;
import com.hnustlgy.securitystudy.entity.User;
import com.hnustlgy.securitystudy.service.LoginService;
import com.hnustlgy.securitystudy.utils.JWTUtils;
import com.hnustlgy.securitystudy.utils.R;
import com.hnustlgy.securitystudy.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public R<String> login(User user) {
        //进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //认证通过给出提示
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登录失败");
        }
        //认证通过生成jwt，并存入R返回
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = JWTUtils.create(userId, null,1000*60*60*24);
        //把用户完整信息存入redis，userId作为key
        redisUtils.set("login"+userId,loginUser);
         return R.success(token);
    }

    @Override
    public R<String> logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        //删除redis中的数据
        redisUtils.del("login"+id);
        return R.success("注销成功");
    }
}
