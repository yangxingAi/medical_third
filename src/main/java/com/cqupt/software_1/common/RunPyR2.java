package com.cqupt.software_1.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunPyR2<T> {
    private Integer code ;
    private String msg;
    private T data1;
    private T data2;
}
