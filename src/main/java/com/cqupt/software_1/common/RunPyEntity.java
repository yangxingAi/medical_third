package com.cqupt.software_1.common;


import lombok.Data;

import java.util.List;

@Data
public class RunPyEntity {

    // 运行表名
    private String tableName;
    // 运行算法名
    private String aiName;
    // 运行算法参数列表
    private List<String> runParams;

}
