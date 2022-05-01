package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Slf4j
public class BasicJeepSalesController implements JeepSalesController{
    @Autowired
    private JeepSalesService jeepSalesService;
    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        log.debug("model={}, trim={}", model, trim);
        return jeepSalesService.fetchJeeps(model,trim);
        //    log.info("model={}, trim={}", model, trim);
    }
    }
