package com.cqupt.software_1.controller;


import com.cqupt.software_1.common.ExecState;
import com.cqupt.software_1.common.R;
import com.cqupt.software_1.dto.TableCreateDTO;
import com.cqupt.software_1.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {


    @Autowired
    private TableService tableService;

    /**
     *
     * 动态创建表
     *
     * @return
     */
    @PostMapping("/createTable")
    public R<ExecState> createTable(@RequestBody TableCreateDTO tableCreateDTO){

        tableService.createTable(tableCreateDTO.getTableName(), tableCreateDTO.getFieldMap());

        return new R<>(200,"成功",ExecState.SUCCESS);
    }

}
