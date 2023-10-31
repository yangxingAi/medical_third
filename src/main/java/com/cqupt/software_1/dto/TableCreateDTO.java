package com.cqupt.software_1.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TableCreateDTO {


    private String tableName;
    private Map<String, String> fieldMap;
}
