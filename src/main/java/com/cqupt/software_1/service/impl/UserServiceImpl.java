package com.cqupt.software_1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.software_1.entity.User;
import com.cqupt.software_1.mapper.UserMapper;
import com.cqupt.software_1.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author hp
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-09-07 14:34:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




