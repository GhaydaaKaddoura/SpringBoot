package com.example.SpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableCaching
public class SpringProjectApplication {


	public static void main(String[] args)
	{
		SpringApplication.run(SpringProjectApplication.class, args);

	}

}
