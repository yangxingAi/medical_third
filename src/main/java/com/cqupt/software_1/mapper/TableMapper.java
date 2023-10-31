package com.cqupt.software_1.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TableMapper {
    void createTable(@Param("tableName") String tableName , @Param("fieldMap") Map<String, String> fieldMap);

    void saveCSV2MySQL(String remotePath,String OriginalFilename);

    void insertTableInfo(String tableName);

}
