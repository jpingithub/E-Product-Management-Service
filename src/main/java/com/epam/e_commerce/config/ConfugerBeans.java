package com.epam.e_commerce.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfugerBeans {
	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}
}
