package io.lance.gradle.mongo.test;

import io.lance.gradle.mongo.MainApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Lance.
 * @time: 2017-12-21 16:05
 * @desc:
 */
@SpringBootTest(classes = MainApplication.class)
public class MongoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void find() {
        Query query = new Query();
        long count = this.mongoTemplate.count(query, "SysLog");
        System.out.printf("count：" + count);
    }


}
