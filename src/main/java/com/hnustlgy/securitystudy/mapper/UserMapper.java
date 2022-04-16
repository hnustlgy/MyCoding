package com.hnustlgy.securitystudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnustlgy.securitystudy.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
