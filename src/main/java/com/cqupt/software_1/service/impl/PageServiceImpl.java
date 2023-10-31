package com.cqupt.software_1.service.impl;

import com.cqupt.software_1.mapper.PageMapper;
import com.cqupt.software_1.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl  implements PageService  {

    @Autowired
    private PageMapper pageMapper;

    @Override
    public List<Map<String, Object>> getInfoByTableName(String tableName) {

        List<Map<String,Object>> res = pageMapper.getInfoByTableName(tableName);

        return res;
    }

    @Override
    public List<Map<String, Object>> getInfoBySelectedFiled(String tableName, String[] params) {

        List<Map<String,Object>> res = pageMapper.getInfoBySelectedFiled(tableName,params);

        return res;
    }
}
