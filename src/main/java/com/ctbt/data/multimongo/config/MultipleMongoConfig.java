package com.ctbt.data.multimongo.config;


import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
    public MongoTemplate firstMongodbTemplate(){
        MongoClient mongoClient = MongoClients.create(firstMongoProperties().getUri());
        return new MongoTemplate(mongoClient, "test");
    }


    @Bean(name = "secondMongodbTemplate")
    public MongoTemplate secondMongodbTemplate(){
        MongoClient mongoClient = MongoClients.create(secondMongoProperties().getUri());
        return new MongoTemplate(mongoClient, "test");
    }


    @Bean(name = "thirdMongodbTemplate")
    public MongoTemplate thirdMongodbTemplate(){
        MongoClient mongoClient = MongoClients.create(thirdMongoProperties().getUri());
        return new MongoTemplate(mongoClient, "test");
    }


    // ================== MongoDbFactory 配置 ===================
//    @Primary
//    @Bean(name = "firstMongodbTemplate")
//    public MongoTemplate firstMongodbTemplate(){
//        MongoDbFactory mongoDbFactory= (MongoDbFactory) new SimpleMongoClientDbFactory(firstMongoProperties().getUri());
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
//        MongoMappingContext mongoMappingContext=new MongoMappingContext();
//        //下划线转驼峰
//        mongoMappingContext.setFieldNamingStrategy(new SnakeCaseFieldNamingStrategy());
//        MongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver,mongoMappingContext);
//        return new MongoTemplate(mongoDbFactory,mongoConverter);
//    }
//
//    @Bean(name = "secondMongodbTemplate")
//    public MongoTemplate secondMongodbTemplate(){
//        MongoDbFactory mongoDbFactory= (MongoDbFactory) new SimpleMongoClientDbFactory(secondMongoProperties().getUri());
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
//        MongoMappingContext mongoMappingContext=new MongoMappingContext();
//        //下划线转驼峰
//        mongoMappingContext.setFieldNamingStrategy(new SnakeCaseFieldNamingStrategy());
//        MongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver,mongoMappingContext);
//        return new MongoTemplate(mongoDbFactory,mongoConverter);
//    }
//
//    @Bean(name = "thirdMongodbTemplate")
//    public MongoTemplate thirdMongodbTemplate(){
//        MongoDbFactory mongoDbFactory= (MongoDbFactory) new SimpleMongoClientDbFactory(thirdMongoProperties().getUri());
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
//        MongoMappingContext mongoMappingContext=new MongoMappingContext();
//        //下划线转驼峰
//        mongoMappingContext.setFieldNamingStrategy(new SnakeCaseFieldNamingStrategy());
//        MongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver,mongoMappingContext);
//        return new MongoTemplate(mongoDbFactory,mongoConverter);
//    }


    // ================== MongoTemplate 配置 ===================

//    @EnableMongoRepositories(
//            basePackages = {
//                    "cn.net.springboot.data.migrate.multimongo.dao.first",
//                    "cn.net.springboot.data.migrate.convert.repository",
//            },
//            mongoTemplateRef = "firstMongoTemplate"
//    )
//    public class FirstMongoTemplate {
//        @Bean
//        public MongoTemplate firstMongoTemplate() {
//            return new MongoTemplate(firstMongoDbFactory(firstMongoProperties()));
//        }
//    }
//
//    @EnableMongoRepositories(basePackages = "cn.net.springboot.data.migrate.multimongo.dao.second", mongoTemplateRef = "secondMongoTemplate")
//    public class SecondMongoTemplate {
//        @Bean
//        public MongoTemplate secondMongoTemplate() {
//            return new MongoTemplate(secondMongoDbFactory(secondMongoProperties()));
//        }
//    }
//
//    @EnableMongoRepositories(basePackages = "cn.net.springboot.data.migrate.multimongo.dao.third", mongoTemplateRef = "thirdMongoTemplate")
//    public class ThirdMongoTemplate {
//        @Bean
//        public MongoTemplate thirdMongoTemplate() {
//            return new MongoTemplate(thirdMongoDbFactory(thirdMongoProperties()));
//        }
//    }
//
//    @EnableMongoRepositories(basePackages = "cn.net.springboot.data.migrate.multimongo.dao.fourth", mongoTemplateRef = "fourthMongoTemplate")
//    public class FourthMongoTemplate {
//        @Bean
//        public MongoTemplate fourthMongoTemplate() {
//            return new MongoTemplate(fourthMongoDbFactory(fourthMongoProperties()));
//        }
//    }
}