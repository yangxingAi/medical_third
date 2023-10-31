package com.cqupt.software_1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.software_1.entity.CardioTrain;
import com.cqupt.software_1.service.CardioTrainService;
import com.cqupt.software_1.mapper.CardioTrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【cardio_train】的数据库操作Service实现
* @createDate 2023-05-16 16:16:01
*/
@Service
public class CardioTrainServiceImpl extends ServiceImpl<CardioTrainMapper, CardioTrain>
    implements CardioTrainService{


    @Autowired
    private CardioTrainMapper cardioTrainMapper;

    @Override
    public List<CardioTrain> getAll() {


        List<CardioTrain> cardioTrains = cardioTrainMapper.selectList(null);

        return cardioTrains;
    }
}




