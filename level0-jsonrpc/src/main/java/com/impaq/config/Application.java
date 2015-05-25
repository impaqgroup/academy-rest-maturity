// tag::runner[]
package com.impaq.config;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.impaq"})
@EnableAutoConfiguration
@EnableSwagger
@EnableJpaRepositories(basePackages = {"com.impaq"})
@EntityScan(basePackages = {"com.impaq"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
