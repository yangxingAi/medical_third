package com.cqupt.software_1.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName t_table_manager
 */
@TableName(value ="t_table_manager")
@Data
public class TableManager implements Serializable {
    private Integer id;

    private String tableName;

    private String tableNameCh;

    private String fieldName;

    private String fieldNameCh;

    private String fieldDesc;

    private String fieldType;

    private String fieldRange;

    private String fileldUnit;

    private String diseaseType;

    private String isDemography;

    private String isPhysiological;

    private String isSociology;

    private String isClinicalRelationship;

    private String isMultipleDiseases;

    private String isZooInformation;

    private String isQuestionnaire;

    private String isTimeInformation;

    private String startTime;

    private String endTime;

    private String timeSpace;

    private String createTime;

    private String updateTime;

    private String tablePeople;

    private String tableOrigin;

    private String tableSize;

    private String tableStatus;


    private Integer userId;
    private static final long serialVersionUID = 1L;
}