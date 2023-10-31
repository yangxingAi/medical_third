package com.cqupt.software_1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.software_1.Util.SecurityUtil;
import com.cqupt.software_1.common.R;
import com.cqupt.software_1.entity.User;
import com.cqupt.software_1.entity.UserLog;
import com.cqupt.software_1.service.UserLogService;
import com.cqupt.software_1.service.UserService;
import com.jcraft.jsch.jce.MD5;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;


/**
 *
 * 用户管理模块
 *
 * 用户注册
 * 用户登录
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @Autowired
    private UserLogService userLogService;

    @PostMapping("/signUp")
    public R signUp(@RequestBody User user) {

        // 检查用户名是否已经存在
        user.setUid(0);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",user.getUsername());

        User existUser = userService.getOne(queryWrapper);

        if (existUser != null){
            return new R<>(500,"用户已经存在",null);
        }

        String pwd = user.getPassword();

        // 对密码进行加密处理

        String password = SecurityUtil.hashDataSHA256(pwd);

        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUpdateTime(null);

        userService.save(user);


        //  操作日志记录

       UserLog userLog = new UserLog();

       QueryWrapper queryWrapper1  = new QueryWrapper<>();
       queryWrapper1.eq("username",user.getUsername());

        User one = userService.getOne(queryWrapper1);
        Integer uid = one.getUid();

        // userLog.setId(1);
       userLog.setUid(uid);
       userLog.setOpTime(new Date());
       userLog.setOpType("用户注册");

       userLogService.save(userLog);

        return new R<>(200,"成功",null);

    }

    @PostMapping("/login")
    public R login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request){

        String userName = user.getUsername();



        QueryWrapper queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("username",user.getUsername());

        User getUser = userService.getOne(queryWrapper);

        String password = getUser.getPassword();
        if (getUser != null){
            // 进行验证密码
            String pwd = user.getPassword();
            String sha256 = SecurityUtil.hashDataSHA256(pwd);
            if (sha256.equals(password)){
                // 验证成功
                UserLog userLog = new UserLog();
                userLog.setUid(getUser.getUid());
                userLog.setOpTime(new Date());
                userLog.setOpType("登录系统");
                userLog.setUsername(userName);
                userLogService.save(userLog);
                // session认证
                HttpSession session = request.getSession();
                session.setAttribute("username",user.getUsername());
                session.setAttribute("userId",getUser.getUid());

                String uid = getUser.getUid().toString();
                Cookie cookie = new Cookie("userId",uid );
                response.addCookie(cookie);

                return new R<>(200,"登录成功",null);
            }else {
                return new R<>(500,"密码错误请重新输入",null);
            }

        }else {
            return new R<>(500,"用户不存在",null);
        }
    }


    @PostMapping("/logout")
    public R logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        session.invalidate();
        return new R<>(200,"退出成功",null);
    }

}
