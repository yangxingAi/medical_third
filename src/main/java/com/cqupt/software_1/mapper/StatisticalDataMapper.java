package com.cqupt.software_1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;



@Mapper
public interface StatisticalDataMapper {
    List<String> getAllTableNameOfDataBase();

    Long getCount(String tableName);

    Long getColumn(String tableName);

    List<String> getColumnsByTableName(String tableName);

    Long getColumnMissCount(String tableName ,String filedName);

    int countTableBeforeDate(@Param("dateParam") String dateParam);
}
