package ru.smirnov.restaurant_voting.config;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ProblemDetail;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.smirnov.restaurant_voting.util.JsonUtil;

import java.sql.SQLException;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@Configuration
@Slf4j
@EnableCaching
@EnableScheduling
@RequiredArgsConstructor
public class AppConfig {

    private final CacheManager cacheManager;

    @Profile("!test")
    @Bean(initMethod = "start", destroyMethod = "stop")
    Server h2Server() throws SQLException {
        log.info("Start H2 TCP server");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

    //   https://stackoverflow.com/a/74630129/548473
    @JsonAutoDetect(fieldVisibility = NONE, getterVisibility = ANY)
    interface MixIn {
        @JsonAnyGetter
        Map<String, Object> getProperties();
    }

    @Autowired
    void configureAndStoreObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new Hibernate5JakartaModule());
        // ErrorHandling: https://stackoverflow.com/questions/7421474/548473
        objectMapper.addMixIn(ProblemDetail.class, MixIn.class);
        JsonUtil.setMapper(objectMapper);
    }

    // clear at midnight
    // https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions/
    @Scheduled(cron = "@midnight")
    public void clearCacheAtMidnight() {
        log.info("clearCacheAtMidnight");
        cacheManager.getCache("allRestaurantsWithMenu").clear();
        cacheManager.getCache("restaurantWithMenu").clear();
    }
}
