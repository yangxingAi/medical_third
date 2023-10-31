package com.cqupt.software_1.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableHeaderDTO {
    private String fieldName;
    private String isDemography;
    private String isPhysiological;
    private String isSociology;
    private String isLabel;

    // 构造函数、getter和setter方法省略
}

