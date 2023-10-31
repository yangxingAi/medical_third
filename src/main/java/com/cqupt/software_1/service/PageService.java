package com.cqupt.software_1.service;

import java.util.List;
import java.util.Map;

public interface PageService {


    List<Map<String, Object>> getInfoByTableName(String tableName);

    List<Map<String, Object>> getInfoBySelectedFiled(String tableName, String[] params);
}
