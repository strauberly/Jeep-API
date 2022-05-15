package com.promineotech.jeep;

import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.testsupport.CreateOrderTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))

public class CreateOrderTest extends CreateOrderTestSupport {
    @Test
    void testCreateOrderReturnSuccess201(){
        //Given: an order as JSON
        String body = createOrderBody();
        String uri = getBaseUriForOrders();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity <String> bodyEntity = new HttpEntity<>(body, headers);

//        When: the order is sent
        ResponseEntity<Order> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, Order.class);

//        Then: a 201 status is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

//        And: the returned order is correct
        assertThat(response.getBody()).isNotNull();

        Order order = response.getBody();
        assertThat(order.getCustomer().getCustomerId()).isEqualTo("MORISON_LINA");
        assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER);
        assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport Altitude");
        assertThat(order.getModel().getNumDoors()).isEqualTo(4);
        assertThat(order.getColor().getColorId()).isEqualTo("EXT_NACHO");
        assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_TURBO");
        assertThat(order.getTire().getTireId()).isEqualTo("35_TOYO");
        assertThat(order.getOptions()).asList().hasSize(6);
    }
}
