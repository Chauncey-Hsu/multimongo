package com.ctbt.data.multimongo.web;

import com.ctbt.data.multimongo.bean.Person;
import com.ctbt.data.multimongo.config.MultipleMongoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
@RequestMapping("/hello")
public class Hello {

    @Autowired
    private MultipleMongoConfig multipleMongoConfig;

    @ResponseBody
    @RequestMapping("/mongo")
    public Object mongo(){
        MongoTemplate mongoTemplateFirst = multipleMongoConfig.firstMongodbTemplate();
        MongoTemplate mongoTemplateSecond = multipleMongoConfig.secondMongodbTemplate();
        MongoTemplate mongoTemplateHot = multipleMongoConfig.thirdMongodbTemplate();

        mongoTemplateFirst.insert(new Person("xu", 27));
        mongoTemplateSecond.insert(new Person("chuan", 28));
        mongoTemplateHot.insert(new Person("qi", 29));

        Person person = mongoTemplateFirst.findOne(new Query(where("name").is("xu")), Person.class);
        Person person1 = mongoTemplateSecond.findOne(new Query(where("name").is("chuan")), Person.class);
        Person person3 = mongoTemplateHot.findOne(new Query(where("name").is("qi")), Person.class);

        ArrayList<Person> personArrayList = new ArrayList<Person>();
        personArrayList.add(person);
        personArrayList.add(person1);
        personArrayList.add(person3);

        System.out.println(personArrayList.toString());
        return personArrayList;
    }
}