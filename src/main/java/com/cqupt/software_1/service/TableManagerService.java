package com.cqupt.software_1.service;

import com.cqupt.software_1.common.TableManagerDTO;
import com.cqupt.software_1.entity.TableManager;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
* @author hp
* @description 针对表【t_table_manager】的数据库操作Service
* @createDate 2023-05-23 15:10:20
*/
public interface TableManagerService extends IService<TableManager> {

    List<TableManager> getAllData();

    List<String> getFiledByTableName(String tableName);

    List<String> getCommentsByTableName(String tableName);

    List<Object> getInfoByTableName(String tableName);

    boolean[] getInfoByFiled(String param);

    List<TableManager> getAllTableManagersByFiledName(List<String> tableNames);

    void insertTableManager(TableManagerDTO tableManagerDTO);


    List<TableManager> getAllDataByUserId(Integer userId);
}
