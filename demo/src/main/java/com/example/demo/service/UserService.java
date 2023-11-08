package com.example.demo.service;

import ch.qos.logback.core.util.FileUtil;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjy
 * @since 2023-04-16
 */
public interface UserService extends IService<User> {
    User getUserByPhoneAndPassword(User user);
    User login(Long id);
}
