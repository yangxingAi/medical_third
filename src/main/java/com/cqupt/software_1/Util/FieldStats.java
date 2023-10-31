package com.cqupt.software_1.Util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldStats {

    private double missingRate;
    private double mean;
    private double variance;
}
