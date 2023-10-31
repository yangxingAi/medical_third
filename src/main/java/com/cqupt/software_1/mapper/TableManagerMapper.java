package com.cqupt.software_1.mapper;

import com.cqupt.software_1.common.TableManagerDTO;
import com.cqupt.software_1.entity.TableManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
* @author hp
* @description 针对表【t_table_manager】的数据库操作Mapper
* @createDate 2023-05-23 15:10:20
* @Entity generator.domain.TTableManager
*/

@Mapper
public interface TableManagerMapper extends BaseMapper<TableManager> {




    List<String> getFiledByTableName(String tableName);

    List<String> getCommentsByTableName(String tableName);

    List<Object> getInfoByTableName(String tableName);

    TableManager getInfoByFiled(String param);

    void insertTableManager(TableManagerDTO tableManagerDTO);
}




