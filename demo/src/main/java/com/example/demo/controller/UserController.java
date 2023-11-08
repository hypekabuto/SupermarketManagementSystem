package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.QueryPageParam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.common.token;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjy
 * @since 2023-04-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @GetMapping("/selectUser")
    public Result selectUser(){
        return Result.success(userMapper.selectList(null));
    }
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        System.out.println(user);
        user.setImageUrl("log.jpg");
        userMapper.insert(user);
    }
    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        userMapper.updateById(user);
    }
    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam("id") String id){
        userMapper.deleteById(Long.parseLong(id));
    }
    @PostMapping("/selectUserByPage")
    public IPage<User> selectUserByPage(@RequestBody QueryPageParam data){
        Page<User> page = new Page<>(data.getPageNum(), data.getPageSize());
        if(data.getParam() == null){
            return userMapper.selectPage(page, null);
        }else {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.allEq(data.getParam());
            return userMapper.selectPage(page, wrapper);
        }
    }
    @PostMapping(value = "/login")
    public String login(@RequestBody User user){
        if (user == null || StringUtils.isEmpty(user.getPassword()) ||
                StringUtils.isEmpty(user.getPassword())){
            return "传过来的手机号或密码为空";
        }
        User loginUser = userService.getUserByPhoneAndPassword(user);
        if (loginUser == null){
            return "手机号或密码错误";
        }else if (loginUser != null){
            String jwtToken = token.getJwtToken(String.valueOf(loginUser.getId()), loginUser.getName());
            User role = userService.login(loginUser.getId());
            System.out.println(role);
            String x = role.getRole();
            String y = role.getName();
            Long z = loginUser.getId();
            user.setId(z);
            userMapper.updateById(user);
            String realRole = "";
            if(x.equals("saleMan")){
                realRole = "销售";
            }else{
                realRole = "管理员";
            }
            jwtToken = jwtToken + "," + realRole + "," + y + ',' + z;
            return jwtToken;
        }
        return null;
    }
    @GetMapping("selectUserById")
    public Result selectUserById(@RequestParam("id") String id){
        return Result.success((userMapper.selectById(id)));
    }
    @PostMapping("/updataUserImage")
    public String upload(@RequestParam("file") MultipartFile file,@RequestParam("userId") Long userId) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        System.out.println(userId);
        String fileName = file.getOriginalFilename();
        String filePath = "D:\\study\\Supermarket Order Management System\\supermarket\\src\\userImage\\";
        File dest = new File(filePath + fileName);
        String truePath = fileName;
        System.out.println(truePath);
        try {
            User user = new User();
            user.setId(userId);
            user.setImageUrl(truePath);
            userMapper.updateById(user);
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            return "上传失败！";
        }
    }

}
