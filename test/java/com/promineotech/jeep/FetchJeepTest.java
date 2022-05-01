package com.promineotech.jeep;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import com.promineotech.jeep.testsupport.FetchJeepTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import java.util.Map;
import java.util.stream.Stream;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.mockito.Mockito.doThrow;

class FetchJeepTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;



@Nested
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
    class TestsThatDoNotPolluteTheApplicationContext extends FetchJeepTestSupport {

    @Test
    void testThatJeepsAreReturnedWhenValidModelAndTrimSupplied() {
        System.out.println(getBaseUri());

//        Given: a valid model, trim and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Sport";
        String uri =
                String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//
        System.out.println(uri);


//        When: a connection is made to the URI
        ResponseEntity<List<Jeep>> response =
                getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

//        Then: a success (OK-200) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        And: the actual list returned is the same as the expected list.
        List<Jeep> actual = response.getBody();
        List<Jeep> expected = buildExpected();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("com.promineotech.jeep.FetchJeepTest#parametersForInvalidInput")
    void testThatErrorReturnedWhenInvalidTrimSupplied(String model, String trim, String reason) {
        System.out.println(getBaseUri());

//        Given: a valid model, trim and URI
        String uri =
                String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//
        System.out.println(uri);

//        When: a connection is made to the URI
        ResponseEntity<Map<String, Object>> response = getRestTemplate()
                .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

//        Then: a not found(404) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        And: an error message is returned.
        Map<String, Object> error = response.getBody();

        assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);


    }

    @Test
    void testThatErrorReturnedWhenUnknownTrimSupplied() {
        System.out.println(getBaseUri());

//        Given: a valid model, trim and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Unknown Trim";
        String uri =
                String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//
        System.out.println(uri);

//        When: a connection is made to the URI
        ResponseEntity<Map<String, Object>> response = getRestTemplate()
                .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

//        Then: a not found(404) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//        And: an error message is returned.
        Map<String, Object> error = response.getBody();

        assertErrorMessageValid(error, HttpStatus.NOT_FOUND);


    }
}

    static Stream<Arguments>parametersForInvalidInput(){
        return Stream.of(
                Arguments.arguments("WRANGLER", "@#$%^%^", "Trim contains non alphanumeric chars"),
                Arguments.arguments("WRANGLER", "C".repeat(Constants.TRIM_MAX_LENGTH+1),
                        "Trim length to long"),
                Arguments.arguments("INVALID", "Sport",
                        "Model is not enum value"));
    }
@Nested
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
    class TestsThatPolluteTheApplicationContext extends FetchJeepTestSupport{
        @MockBean
        private JeepSalesService jeepSalesService;

    @Test
    void testThatAnUnplannedErrorResultsInA500Status(){
//        System.out.println(getBaseUri());

//        Given: a valid model, trim and URI
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Invalid";
        String uri =
                String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//
        doThrow(new RuntimeException("Ouch!")).when(jeepSalesService)
                        .fetchJeeps(model, trim);
//        System.out.println(uri);

//        When: a connection is made to the URI
        ResponseEntity<Map<String, Object>> response = getRestTemplate()
                .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

//        Then: an internal server error is returned(500) status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//        And: an error message is returned.
        Map<String, Object> error = response.getBody();

        assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}

}
