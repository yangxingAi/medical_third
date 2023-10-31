package com.cqupt.software_1.controller;

import com.cqupt.software_1.common.DataTable;
import com.cqupt.software_1.common.R;
import com.cqupt.software_1.common.TableManagerDTO;
import com.cqupt.software_1.common.UploadResult;
import com.cqupt.software_1.entity.TableManager;
import com.cqupt.software_1.entity.UserLog;
import com.cqupt.software_1.mapper.DataTableManagerMapper;
import com.cqupt.software_1.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 字段管理工具
 *
 * @author yangxing
 * @since 2023/8/2
 */


@RestController
@RequestMapping("/filedManager")
public class FiledManagerController {

    @Autowired
    private TableManagerService tableManagerService;


    @Autowired
    private UserLogService userLogService;



    @Resource
    private DataTableManagerMapper dataTableManagerMapper;

    /**
     *
     *
     *  查询所有的字段
     * @return
     */
    @GetMapping("/queryTableManager")
    public R<List<TableManager>> queryTableManger(int pageNum,HttpServletRequest request){


        PageHelper.startPage(pageNum,15);

        Integer userId = (Integer) request.getSession().getAttribute("userId");



        List<TableManager> allData = tableManagerService.getAllDataByUserId(userId);



        PageInfo pageInfo = new PageInfo<>(allData);

        return new R<>(200,"成功",allData,pageInfo.getPages());

    }


    // 编辑字段
    @PostMapping("/updateFiled")
    public R updateFiled(@RequestBody TableManager tableManager,HttpServletRequest request){

       tableManagerService.updateById(tableManager);

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // 操作日志
        UserLog userLog = new UserLog();
        userLog.setUid(userId);
        userLog.setOpTime(new Date());


        userLog.setOpType("更新字段: "+ tableManager.getFieldName());


        userLogService.save(userLog);

        return new R<>(200,"成功",null);

    }



    // 删除字段
    @PostMapping("/delFiled")
    public R delFiled(@RequestBody TableManager tableManager, HttpServletRequest request){

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // 操作日志
        UserLog userLog = new UserLog();
        userLog.setUid(userId);
        userLog.setOpTime(new Date());



        userLog.setOpType("删除字段: "+ tableManager.getFieldName());


        userLogService.save(userLog);


        boolean b = tableManagerService.removeById(tableManager.getId());
        if (b == true){

            return new R<>(200,"删除成功",null);
        }

        return new R<>(500,"删除失败",null);
    }






















}
