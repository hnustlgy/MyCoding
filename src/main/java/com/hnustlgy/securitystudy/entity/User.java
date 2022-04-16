package com.hnustlgy.securitystudy.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private String phone;
    private String sex;
    private String avator;
    private String userType;
}
