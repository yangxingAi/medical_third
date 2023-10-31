package com.cqupt.software_1.service;

import com.cqupt.software_1.entity.CardioTrain;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【cardio_train】的数据库操作Service
* @createDate 2023-05-16 16:16:01
*/
public interface CardioTrainService extends IService<CardioTrain> {

    List<CardioTrain> getAll();
}
