package com.cqupt.software_1.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="data_table")
public class DataTable implements Serializable {


    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("table_desc")
    private String table_desc;
    @TableField("table_name")
    private String table_name;
    private Integer featurenumber;
    private Integer samplesize;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp create_time;
    @TableField("tableType")
    private String table_type;
    private String disease;
    private String creator;
}
