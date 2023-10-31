package com.cqupt.software_1.service.impl;

import com.cqupt.software_1.entity.TableManager;
import com.cqupt.software_1.mapper.TableMapper;
import com.cqupt.software_1.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public void createTable(String tableName, Map<String, String> fieldMap) {

         tableMapper.createTable(tableName, fieldMap);

    }

    @Override
    public void saveCSV2MySQL(String remotePath, String originalFilename) {
        tableMapper.saveCSV2MySQL(remotePath,originalFilename);
    }

    @Override
    public void insertTableInfo(String tableName) {

        tableMapper.insertTableInfo(tableName);
    }
}
