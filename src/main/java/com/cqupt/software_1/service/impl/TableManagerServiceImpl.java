package com.cqupt.software_1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.software_1.common.TableManagerDTO;
import com.cqupt.software_1.entity.TableManager;
import com.cqupt.software_1.service.TableManagerService;
import com.cqupt.software_1.mapper.TableManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
* @author hp
* @description 针对表【t_table_manager】的数据库操作Service实现
* @createDate 2023-05-23 15:10:20
*/
@Service
public class TableManagerServiceImpl extends ServiceImpl<TableManagerMapper, TableManager>
    implements TableManagerService {


    @Autowired
    private TableManagerMapper tableManagerMapper;



    @Override
    public void insertTableManager(TableManagerDTO tableManagerDTO) {
        tableManagerMapper.insertTableManager(tableManagerDTO);
    }

    @Override
    public List<TableManager> getAllDataByUserId(Integer userId) {


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        List<TableManager> tableManager = tableManagerMapper.selectList(queryWrapper);

        return tableManager;
    }

    @Override
    public List<TableManager> getAllData() {



        List<TableManager> tableManager = tableManagerMapper.selectList(null);


        return tableManager;
    }

    @Override
    public List<String> getFiledByTableName(String tableName) {


        List<String> tableNames = tableManagerMapper.getFiledByTableName(tableName);

        return tableNames;
    }

    @Override
    public List<String> getCommentsByTableName(String tableName) {

        List<String> comments = tableManagerMapper.getCommentsByTableName(tableName);

        return comments;
    }

    @Override
    public List<Object> getInfoByTableName(String tableName) {



        List<Object> res = tableManagerMapper.getInfoByTableName(tableName);

        return res;
    }

    @Override
    public boolean[] getInfoByFiled(String param) {


        boolean r1 = false,r2 = false, r3 = false;
        TableManager tableManager = tableManagerMapper.getInfoByFiled(param);

        if (tableManager != null){

            r1 = Objects.equals(tableManager.getIsDemography(), "1");
           r2 = Objects.equals(tableManager.getIsPhysiological(), "1");
             r3 = Objects.equals(tableManager.getIsSociology(), "1");
        }

        return new boolean[]{r1,r2,r3};
    }

    @Override
    public List<TableManager> getAllTableManagersByFiledName(List<String> tableNames) {

        List<TableManager> res= new ArrayList<>();

        for (int i = 0; i< tableNames.size() ; i++) {
            QueryWrapper queryWrapper = new QueryWrapper();

            queryWrapper.eq("field_name", tableNames.get(i));
            queryWrapper.eq("table_name","Diabetes");
            TableManager tableManager = tableManagerMapper.selectOne(queryWrapper);
            res.add(tableManager);
        }

        return res;
    }
}




