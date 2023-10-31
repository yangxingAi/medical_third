package com.cqupt.software_1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName cardio_train
 */
@TableName(value ="cardio_train")
@Data
public class CardioTrain implements Serializable {
    private Integer age;

    private Integer height;

    private Float weight;

    private Integer apHi;

    private Integer apLo;

    private Integer id;

    private Integer gender;

    private Integer cholesterol;

    private Integer gluc;

    private Integer smoke;

    private Integer alco;

    private Integer active;

    private Integer cardio;

    private static final long serialVersionUID = 1L;
}