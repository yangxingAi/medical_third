package com.cqupt.software_1.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_1.common.DataTable;
import com.cqupt.software_1.mapper.DataTableManagerMapper;
import com.cqupt.software_1.service.DataTableManagerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class DataTableManagerServiceImpl implements DataTableManagerService {


    String  dbUrl = "jdbc:mysql://10.16.48.219:3306/medical?characterEncoding=utf-8";
    String dbUsername = "root";
    String dbPassword = "111111";

    @Resource
    private DataTableManagerMapper dataTableManagerMapper;


    @Override
    public List<DataTable> upalldata() {
        return dataTableManagerMapper.upalldata();
    }


    @Override
    public void deletename(String tablename) {
        dataTableManagerMapper.deletetablename(tablename);
    }

    @Override
    public List<String> upname() {
        return dataTableManagerMapper.upname();
    }

    @Override
    public void updateDataTable(String table_name,String disease) {
        // 在这里实现计算样本数和特征数的逻辑
        int featurenumber = calculateFeatureNumber(table_name);
        int samplesize = calculateSampleSize(table_name);

        // 创建一个新的数据对象
        DataTable dataTableEntity = new DataTable();
        dataTableEntity.setTable_name(table_name);
        dataTableEntity.setFeaturenumber(featurenumber);
        dataTableEntity.setSamplesize(samplesize);
//        dataTableEntity.setTableType("Your Table Type"); // 替换为表类型信息
        dataTableEntity.setDisease(disease); // 替换为疾病信息
        dataTableEntity.setCreator("杨星"); // 替换为创建者信息

        // 插入新样本数据到data_table表中
        dataTableManagerMapper.insertDataTable(dataTableEntity);
    }

    private int calculateFeatureNumber(String tableName) {
        try {
            // 建立数据库连接


            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement();

            // 查询表头
            String query = "SELECT * FROM " +"`"+tableName+"`" + " LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);
            int featureNumber = resultSet.getMetaData().getColumnCount();

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();

            return featureNumber;
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return 0; // 或者抛出异常
        }
    }

    // 计算样本数的方法
    private int calculateSampleSize(String tableName) {
        try {
            // 建立数据库连接

            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Statement statement = connection.createStatement();

            // 查询数据行数
            String query = "SELECT COUNT(*) AS rowCount FROM " + "`"+tableName+"`";
            ResultSet resultSet = statement.executeQuery(query);
            int rowCount = 0;
            if (resultSet.next()) {
                rowCount = resultSet.getInt("rowCount");
            }

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();

            // 减去表头行数（假设第一行是表头）
            return rowCount - 1;
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return 0; // 或者抛出异常
        }
    }

    @Override
    public boolean save(DataTable dataTable) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<DataTable> collection, int i) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<DataTable> collection) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<DataTable> collection, int i) {
        return false;
    }

    @Override
    public boolean removeById(Serializable serializable) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> map) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<DataTable> wrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> collection) {
        return false;
    }

    @Override
    public boolean updateById(DataTable dataTable) {
        return false;
    }

    @Override
    public boolean update(DataTable dataTable, Wrapper<DataTable> wrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<DataTable> collection, int i) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(DataTable dataTable) {
        return false;
    }

    @Override
    public DataTable getById(Serializable serializable) {
        return null;
    }

    @Override
    public Collection<DataTable> listByIds(Collection<? extends Serializable> collection) {
        return null;
    }

    @Override
    public Collection<DataTable> listByMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public DataTable getOne(Wrapper<DataTable> wrapper, boolean b) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<DataTable> wrapper) {
        return null;
    }

    @Override
    public Object getObj(Wrapper<DataTable> wrapper) {
        return null;
    }

    @Override
    public int count(Wrapper<DataTable> wrapper) {
        return 0;
    }

    @Override
    public List<DataTable> list(Wrapper<DataTable> wrapper) {
        return null;
    }

    @Override
    public IPage<DataTable> page(IPage<DataTable> iPage, Wrapper<DataTable> wrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<DataTable> wrapper) {
        return null;
    }

    @Override
    public List<Object> listObjs(Wrapper<DataTable> wrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<DataTable> iPage, Wrapper<DataTable> wrapper) {
        return null;
    }
}
