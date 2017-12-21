package io.lance.gradle.common.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Lance.
 * @time: 2017-12-21 16:02
 * @desc:
 */
@Configuration
@ImportResource(locations = "classpath:spring/mongodb.xml")
public class MongoConfig {
}
