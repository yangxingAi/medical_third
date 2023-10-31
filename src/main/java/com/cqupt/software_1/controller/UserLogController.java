package com.cqupt.software_1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.software_1.common.R;
import com.cqupt.software_1.entity.UserLog;
import com.cqupt.software_1.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/userlog")
public class UserLogController {



   @Autowired
   private UserLogService userLogService;


    @GetMapping("/allLog")
    public R<List<UserLog>> queryAllLog(HttpServletRequest request , HttpServletResponse response){


        Integer userId = (Integer) request.getSession().getAttribute("userId");

        System.out.println(userId);

        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("uid",userId);

        List list = userLogService.list(queryWrapper);

        return new R<>(200,"成功",list);

    }
}
