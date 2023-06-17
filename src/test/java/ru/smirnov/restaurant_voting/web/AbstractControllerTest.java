package ru.smirnov.restaurant_voting.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public abstract class AbstractControllerTest {
    @BeforeAll
    static void checkCloseMidnight() {
        LocalTime now = LocalTime.now();
        if (now.isAfter(LocalTime.MAX.minus(5, ChronoUnit.MINUTES)) ||
                now.isBefore(LocalTime.MIN.plus(5, ChronoUnit.MINUTES))) {
            log.error("+++ Close to midnight dates in DB and test data may differs, re-voting and tests result might be wrong!");
        }
    }

    @Autowired
    private MockMvc mockMvc;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}
