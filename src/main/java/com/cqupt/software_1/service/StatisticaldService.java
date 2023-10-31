package com.cqupt.software_1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticaldService {
    Map<String, Object> getStatisticaldData();

    int showTableTrend(LocalDate minusDays);
}
