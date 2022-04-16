package com.hnustlgy.securitystudy.filter;

import com.hnustlgy.securitystudy.entity.LoginUser;
import com.hnustlgy.securitystudy.utils.JWTUtils;
import com.hnustlgy.securitystudy.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//配置校验过滤器
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        Claims claims = JWTUtils.parse(token);
        String id = claims.getSubject();
        //从reids中取出用户信息
        String redisKey = "login"+id;
        LoginUser loginUser = (LoginUser) redisUtils.get(redisKey);
        if(Objects.isNull(loginUser))
        {
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        //这里使用三个参数的构造器，会自动设置其认证状态为true
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
