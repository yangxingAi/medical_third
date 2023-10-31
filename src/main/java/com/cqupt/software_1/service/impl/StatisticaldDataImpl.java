package com.cqupt.software_1.service.impl;

import com.cqupt.software_1.mapper.StatisticalDataMapper;
import com.cqupt.software_1.service.StatisticaldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.datatransfer.FlavorEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StatisticaldDataImpl implements StatisticaldService {


    @Autowired
    private StatisticalDataMapper statisticaldDataMapper;

    @Override
    public Map<String,Object> getStatisticaldData() {

        // 查询到数据库的所有的表名
        List<String> tableNames = statisticaldDataMapper.getAllTableNameOfDataBase();

        for (String tableName : tableNames) {

            System.out.println(tableName);
        }

        Map<String ,Object> resMap = new HashMap<>();

        // 总的行数
        Long rowCount = 0L;
        // 总的列数
        Long columnCount = 0L;
        // 总体缺失总数
        int missCount = 0;

        for (String tableName : tableNames) {

            Long row = statisticaldDataMapper.getCount(tableName);

            Long column = statisticaldDataMapper.getColumn(tableName);
            // 计算每个表的缺失总数
            int missNum = calculatorMissRate4Table(tableName);
            missCount += missNum;

            rowCount += row;
            columnCount +=  column;
        }

        // 总体缺失 / 总体数据




        double missRateTotal = (double) missCount/(rowCount*columnCount)*100;


        // 格式化两位小数
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        System.out.println(decimalFormat.format(missRateTotal));
        resMap.put("数据总量" , rowCount);
        resMap.put("指标总量" , columnCount);
        resMap.put("总体缺失率", decimalFormat.format(missRateTotal));
        resMap.put("总体有效率", decimalFormat.format(1 - missRateTotal));


        return resMap;
    }

    @Override
    public int showTableTrend(LocalDate minusDays) {

        // 根据当前日期查询在今天和以前的所有表数量

        int num = statisticaldDataMapper.countTableBeforeDate(minusDays.toString());

        return num;
    }

    /**
     *
     *  根据表名计算每个表的 缺失总数
     * @param tableName
     * @return
     */
    private int calculatorMissRate4Table(String tableName) {

        // 1. 获取表的总记录数


        Long row = statisticaldDataMapper.getCount(tableName);

        // 2. 根据表名获取所有的字段名字

        List<String> filedNames = statisticaldDataMapper.getColumnsByTableName(tableName);

        // 3. 遍历所有的字段，统计为null 的缺失总数
        int missCount = 0;
        for (String filedName : filedNames) {
            Long missNum = statisticaldDataMapper.getColumnMissCount(tableName, filedName);
            missCount += missNum;
        }


        return missCount;

    }
}
