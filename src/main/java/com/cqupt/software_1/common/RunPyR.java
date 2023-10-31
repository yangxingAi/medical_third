package com.cqupt.software_1.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunPyR<T> {
    private Integer code ;
    private String msg;
    private T data;
}
