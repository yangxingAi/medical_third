package com.cqupt.software_1.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.software_1.entity.Model;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelMapper extends BaseMapper<Model> {
               List<Model> all();
               List<Model> getById(Integer id);
               void updata(Model model);

}
