package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjy
 * @since 2023-04-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByPhoneAndPassword(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("phone", user.getPhone());
        wrapper.eq("password", user.getPassword());
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public User login(Long id) {
        return userMapper.login(id);
    }

}
