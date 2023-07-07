package com.streak.authservice;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories(basePackages = "com.streak.authservice.repository")
@Slf4j
public class MongoConfig {

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;
    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.conn}")
    private String connectionUrl;

    @Bean
    public MongoClient mongo() {
        //String connectionUrl = "mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + database;
        log.info("Mongo connection String {}", connectionUrl);
        ConnectionString connectionString = new ConnectionString(connectionUrl);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder ->
                        builder.maxConnectionIdleTime(60000, TimeUnit.MILLISECONDS))
                .applyToSocketSettings(builder -> builder.readTimeout(60000, TimeUnit.MILLISECONDS))
                .applyToSocketSettings(builder -> builder.connectTimeout(60000, TimeUnit.MILLISECONDS))
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), database);
    }
}