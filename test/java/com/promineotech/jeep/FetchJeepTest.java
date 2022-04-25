package com.promineotech.jeep;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.testsupport.FetchJeepTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.aspectj.bridge.MessageUtil.fail;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
class FetchJeepTest  extends FetchJeepTestSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testDB(){
       int numrows = JdbcTestUtils.countRowsInTable(jdbcTemplate,"customers");
        System.out.println("num= " + numrows);
    }

    @Disabled
    @Test
    void testThatJeepsAreReturnedWhenValidModelAndTrimSupplied(){
        System.out.println(getBaseUri());

//        Given: a valid model, trim and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Sport";
        String uri =
                String.format("http://localhost:%d/jeeps?model=%s&trim=%s", getServerPort(), model, trim);
//                String.format("%s?model=%s$trim=%s",getBaseUri(),model,trim);
        System.out.println(uri);



//        When: a connection is made to the URI
        ResponseEntity<List<Jeep>> response =
                getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

//        Then: a success (OK-200) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        And: the actual list returned is the same as the expected list.
    List<Jeep> expected = buildExpected();
        assertThat(response.getBody()).isEqualTo(expected);
    }
}
