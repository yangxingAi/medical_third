package com.cqupt.software_1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PageMapper  {

    List<Map<String, Object>> getInfoByTableName(@Param("tableName") String tableName);

    List<Map<String, Object>> getInfoBySelectedFiled(@Param("tableName")String tableName, @Param("params") String[] params);
}
