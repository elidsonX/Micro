package com.example.delivery.delivery_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DeliveryBackendApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DISH", Integer.class);
        assertEquals(3, count);
    }
}