package com.dsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//(exclude = {SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class})
//@ComponentScan({"com.dsp", "com.dsp.user"})
//@EnableJpaRepositories()
//(exclude = {DataSourceAutoConfiguration.class })
//@PropertySource("classpath:application.properties")
public class DspApplication {
	public static void main(String[] args) {
		SpringApplication.run(DspApplication.class, args);	
	}
}
