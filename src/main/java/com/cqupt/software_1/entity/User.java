package com.cqupt.software_1.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {


    @TableId
    private Integer uid;

    private String username;

    private String password;

    private Integer role;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}