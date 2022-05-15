package com.promineotech.jeep.dao;

import com.promineotech.jeep.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface JeepOrderDao {

    Optional<Customer> fetchCustomer(String customerId);

    Optional<Jeep> fetchModel(JeepModel model, String trim, int doors);

    Optional<Color> fetchColor(String colorId);

    Optional<Engine> fetchEngine(String engineId);

    Optional<Tire> fetchTire(String tireId);

    Order saveOrder(Customer customer, Jeep jeep, Color color, Engine engine,
                    Tire tire, BigDecimal price, List<Option> options);

    List<Option> fetchOptions(List<String> optionIds);
}
