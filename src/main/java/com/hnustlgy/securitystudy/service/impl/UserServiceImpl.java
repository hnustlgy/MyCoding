package com.hnustlgy.securitystudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnustlgy.securitystudy.entity.User;
import com.hnustlgy.securitystudy.mapper.UserMapper;
import com.hnustlgy.securitystudy.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
