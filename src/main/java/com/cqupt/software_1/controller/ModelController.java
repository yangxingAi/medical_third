package com.cqupt.software_1.controller;


import com.cqupt.software_1.entity.Model;
import com.cqupt.software_1.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Model")
public class ModelController {


    @Autowired
    private ModelService modelService;

    @GetMapping("/all")
    public List<Model> upall(){
        return modelService.all();
    }

}
