package com.hnustlgy.securitystudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnustlgy.securitystudy.entity.User;
import com.hnustlgy.securitystudy.utils.R;

public interface LoginService{
    R<String> login(User user);

    R<String> logout();
}
