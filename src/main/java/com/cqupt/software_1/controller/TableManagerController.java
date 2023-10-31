package com.cqupt.software_1.controller;


import com.cqupt.software_1.common.DataTable;
import com.cqupt.software_1.common.R;
import com.cqupt.software_1.common.TableManagerDTO;
import com.cqupt.software_1.common.UploadResult;
import com.cqupt.software_1.entity.TableManager;
import com.cqupt.software_1.mapper.DataTableManagerMapper;
import com.cqupt.software_1.service.DataTableManagerService;
import com.cqupt.software_1.service.FileService;
import com.cqupt.software_1.service.TableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tTableManager")
public class TableManagerController {

    @Autowired
    private DataTableManagerService dataTableManagerService;


    @Resource
    private DataTableManagerMapper dataTableManagerMapper;

    @Autowired
    private TableManagerService tableManagerService;




    /**
     * 上传文件的字段分类
     * @param tableManagerDTO
     * @return
     */
    @PostMapping("/insertTableManager")
    public UploadResult insertTableManager(@RequestBody TableManagerDTO tableManagerDTO) {
        try {
            tableManagerService.insertTableManager(tableManagerDTO);
            List<DataTable> d=dataTableManagerService.upalldata();
            UploadResult res =new UploadResult();
            res.setRes(d);
            return res;
        } catch (Exception e) {
            System.out.println("出错了");
            UploadResult res = new UploadResult();
            res.setCode(500);
            res.setE(e);
            dataTableManagerService.deletename(tableManagerDTO.getTableName());
            dataTableManagerMapper.deletetablename(tableManagerDTO.getTableName());
            return res;
        }
    }


    /**
     *
     * 编辑字段属性     写入操作到操作日志
     *
     * @param tableManager
     * @return
     */
    @PostMapping("/updateTableManager")
    public R updateTableManager(@RequestBody TableManager tableManager){

        boolean b  = tableManagerService.updateById(tableManager);

        if (b == true) {
            return new R<>(200 , "更新字段成功", null);
        }else {
            return new R<>(500,"更新失败",null);
        }
    }





}
