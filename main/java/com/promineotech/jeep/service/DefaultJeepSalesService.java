package com.promineotech.jeep.service;

import com.promineotech.jeep.entity.Jeep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService{
    @Override
    public List<Jeep> fetchJeeps(String model, String trim) {
      log.info("The fetch jeeps method was call with model = {} & trim = {}", model, trim);
        return null;
    }
}
