package com.promineotech.jeep.testsupport;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

public class BaseTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    @Getter
    private TestRestTemplate restTemplate;


    protected String getBaseUriForJeeps(){
        return String.format("http://localhost:%d/jeeps", serverPort);

    }

    protected String getBaseUriForOrders(){
        return String.format("http://localhost:%d/orders", serverPort);

    }
}
