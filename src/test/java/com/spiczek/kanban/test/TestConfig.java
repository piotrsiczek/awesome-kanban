package com.spiczek.kanban.test;

import com.spiczek.kanban.apis.BoardApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author Piotr Siczek
 */
@Configuration
@ComponentScan(basePackages = {"com.spiczek.kanban.test.behaviour"})
public class TestConfig {

	@Bean
	@Autowired
	public BoardApi boardApi(MongoTemplate mongoTemplate) {
		return new BoardApi(mongoTemplate);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
