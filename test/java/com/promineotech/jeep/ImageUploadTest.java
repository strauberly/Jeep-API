package com.promineotech.jeep;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(
        scripts = {"classpath:flyway/migrations/v1.0__Jeep_Schema.sql",
                "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))


public class ImageUploadTest {

     private static final String JEEP_IMAGE = "flyway/Jeep-Nacho-Concept.jpg";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testThatServerCorrectlyReceivesAnImageAndReturnsOKResponse() throws Exception {
        int numRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "images");
        Resource image = new ClassPathResource(JEEP_IMAGE);

        if(!image.exists()) {
            fail("Could not find resource %s", JEEP_IMAGE);
        }
        try(InputStream inputStream = image.getInputStream()){
            MockMultipartFile file = new MockMultipartFile("image", JEEP_IMAGE,
                    MediaType.TEXT_PLAIN_VALUE, inputStream);

            MvcResult result = mockMvc
                    .perform(MockMvcRequestBuilders
                            .multipart("/jeeps/1/image")
                            .file(file))
                    .andDo(print())
                    .andExpect(status().is(201))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            assertThat(content).isNotEmpty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}