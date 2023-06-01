package com.Elastic8SpringBoot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class ElasticwithSpringBoot3Application {

	public static void main(String[] args) {
		SpringApplication.run(ElasticwithSpringBoot3Application.class, args);
	}
}
