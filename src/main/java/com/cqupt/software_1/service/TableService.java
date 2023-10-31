package com.cqupt.software_1.service;

import java.util.List;
import java.util.Map;

public interface TableService {
    void createTable(String tableName, Map<String, String> fieldMap);

    void saveCSV2MySQL(String remotePath, String originalFilename);

    void insertTableInfo(String tableName);
}
