package com.cqupt.software_1.controller;



import com.cqupt.software_1.common.DataTable;
import com.cqupt.software_1.common.TableManagerDTO;
import com.cqupt.software_1.common.UploadResult;
import com.cqupt.software_1.mapper.DataTableManagerMapper;
import com.cqupt.software_1.service.DataTableManagerService;
import com.cqupt.software_1.service.FileService;
import com.cqupt.software_1.service.TableManagerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/DataTable")
public class DataTableManagerController {

    @Autowired
    private DataTableManagerService dataTableManagerService;

    @Autowired
    private FileService fileService;

    @Resource
    private DataTableManagerMapper dataTableManagerMapper;

    @Autowired
    private TableManagerService tableManagerService;






    public DataTableManagerController(DataTableManagerService dataTableManagerService, FileService fileService, TableManagerService tableManagerService) {
        this.dataTableManagerService = dataTableManagerService;
        this.fileService = fileService;
        this.tableManagerService = tableManagerService;
    }

    /**
     * 获取表管理表所有信息
     *
     * @return
     */
    @GetMapping("/upall")
    public List<DataTable> upall() {
        return dataTableManagerService.upalldata();

    }




    @GetMapping("/inspection")
    public boolean test_name(String name){
        List<String> usedTableNames = dataTableManagerService.upname();
        if (usedTableNames.contains(name)) {
            return  false;
        }else{
            return true;
        }
    }



    /**
     *  从陈鹏那里拷贝来
     */


    @PostMapping("/upload")
    public UploadResult uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("newName") String newName, @RequestParam("disease") String disease) {
        try {

            return fileService.fileUpload(file, newName,disease);
        } catch (Exception e) {
            UploadResult res =new UploadResult();
            res.setCode(500);
            System.out.println("错误信息: " + e);
            res.setE(e);
            return res;
        }
    }






}