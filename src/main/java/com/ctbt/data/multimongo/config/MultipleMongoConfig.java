package com.ctbt.data.multimongo.config;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MultipleMongoConfig {
    // ================== MongoProperties 配置 ===================

    @Primary
    @Bean
    @ConfigurationProperties("spring.data.mongodb.first")
    public MongoProperties firstMongoProperties() {
        System.out.println("================== firstMongoProperties 配置 ===================");
        return new MongoProperties();
    }

    @Bean
    @ConfigurationProperties("spring.data.mongodb.second")
    public MongoProperties secondMongoProperties() {
        System.out.println("================== secondMongoProperties 配置 ===================");
        return new MongoProperties();
    }

    @Bean
    @ConfigurationProperties("spring.data.mongodb.third")
    public MongoProperties thirdMongoProperties() {
        System.out.println("================== thirdMongoProperties 配置 ===================");
        return new MongoProperties();
    }


    @Primary
    @Bean(name = "firstMongodbTemplate")
    public MongoTemplate firstMongodbTemplate() {
        MongoClient mongoClient = MongoClients.create(firstMongoProperties().getUri());
        return new MongoTemplate(mongoClient, "test");
    }


    @Bean(name = "secondMongodbTemplate")
    public MongoTemplate secondMongodbTemplate() {
        MongoClient mongoClient = MongoClients.create(secondMongoProperties().getUri());
        return new MongoTemplate(mongoClient, "test");
    }


    @Bean(name = "thirdMongodbTemplate")
    public MongoTemplate thirdMongodbTemplate() {
        MongoClient mongoClient = MongoClients.create(thirdMongoProperties().getUri());
        return new MongoTemplate(mongoClient, "test");
    }


}