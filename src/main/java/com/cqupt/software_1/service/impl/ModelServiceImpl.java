package com.cqupt.software_1.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.software_1.entity.Model;
import com.cqupt.software_1.mapper.ModelMapper;
import com.cqupt.software_1.service.ModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ModelServiceImpl implements ModelService {
    @Resource
    ModelMapper modelMapper;

    @Override
    public List<Model> all() {
        return modelMapper.all();
    }

    @Override
    public List<Model> getById(Integer id) {
        return modelMapper.getById(id);
    }

    @Override
    public String updata(Model model) {
        modelMapper.updata(model);
        return "修改成功";
    }


    @Override
    public boolean save(Model model) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<Model> collection, int i) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Model> collection) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Model> collection, int i) {
        return false;
    }

    @Override
    public boolean removeById(Serializable serializable) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> map) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<Model> wrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> collection) {
        return false;
    }

    @Override
    public boolean updateById(Model model) {
        return false;
    }

    @Override
    public boolean update(Model model, Wrapper<Model> wrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Model> collection, int i) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Model model) {
        return false;
    }

    @Override
    public Model getById(Serializable serializable) {
        return null;
    }

    @Override
    public Collection<Model> listByIds(Collection<? extends Serializable> collection) {
        return null;
    }

    @Override
    public Collection<Model> listByMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public Model getOne(Wrapper<Model> wrapper, boolean b) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Model> wrapper) {
        return null;
    }

    @Override
    public Object getObj(Wrapper<Model> wrapper) {
        return null;
    }

    @Override
    public int count(Wrapper<Model> wrapper) {
        return 0;
    }

    @Override
    public List<Model> list(Wrapper<Model> wrapper) {
        return null;
    }

    @Override
    public IPage<Model> page(IPage<Model> iPage, Wrapper<Model> wrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<Model> wrapper) {
        return null;
    }

    @Override
    public List<Object> listObjs(Wrapper<Model> wrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<Model> iPage, Wrapper<Model> wrapper) {
        return null;
    }
}
