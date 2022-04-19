package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class BasicJeepSalesController implements JeepSalesController{
    @Override
    public List<Jeep> fetchJeeps(String model, String trim) {
        return null;
    }
}
