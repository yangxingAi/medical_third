package com.cqupt.software_1.controller;


import com.cqupt.software_1.common.R;

import com.cqupt.software_1.entity.TableManager;

import com.cqupt.software_1.service.CardioTrainService;
import com.cqupt.software_1.service.StatisticaldService;
import com.cqupt.software_1.service.TableManagerService;
import com.cqupt.software_1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/index")
public class IndexController {


    @Autowired
    private CardioTrainService cardioTrainService;

    @Autowired
    private UserService userService;



    @Autowired
    private TableManagerService tableManagerService;



    @Autowired
    private StatisticaldService statisticaldService;


    /**
     *
     * 获取所有的表信息
     * @return
     */
    @GetMapping("/getAllData")
    public R<List<TableManager>> getAllData(){

        List<TableManager> tableList = tableManagerService.getAllData();
        // 去重
        tableList = tableList.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(
                        ()-> new TreeSet<>(
                                Comparator.comparing(TableManager::getTableName)
                        )
                ), ArrayList::new
        ));

        return new R<>(200,"成功",tableList);
    }


    /**
     *
     *  获取数据总量 ， 指标总量， 总体缺失率 ， 总体有效率
     *
     * @return
     */

    @GetMapping("/getStatisticaldData")
    public R<Map<String ,Object>> getStatisticaldData(){

       Map<String,Object> res = statisticaldService.getStatisticaldData();

       return new R<>(200,"成功",res);

    }


    /**
     * 获取7天内的数据表趋势表
     *
     * @return
     */
    @GetMapping("/showTableTrend")
    public R<Map<String, Integer>> showTableTrend(){

        // 前 7 天
        int previousDays = 7 ;
        // 获取到当天的时间
        LocalDate currentDate =  LocalDate.now();
        Map<String , Integer>  res = new HashMap<>();
        // 遍历从7 天前的每一天数据表总
        for (int i = 0; i < previousDays; i++) {
            // 获取到前几天
            LocalDate minusDays = currentDate.minusDays(i);
            // 获取到具体时间的表总量
            int tableNum = statisticaldService.showTableTrend(minusDays);

            res.put(minusDays.toString(), tableNum);

        }

        return new R<>(200, "成功", res);
    }




    @GetMapping("/getUserInfo")
    public R getUserInfo(HttpServletRequest request){



        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        return new R<>(200,"获取用户信息成功",username);
    }

}
