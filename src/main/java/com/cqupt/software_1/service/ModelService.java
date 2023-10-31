package com.cqupt.software_1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.software_1.entity.Model;


import java.util.List;

public interface ModelService extends IService<Model> {


          List<Model> all();
          List<Model> getById(Integer id);
          String updata(Model model);

}
