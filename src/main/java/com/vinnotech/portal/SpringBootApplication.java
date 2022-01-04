package com.vinnotech.portal;

import org.springframework.boot.SpringApplication;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

	/*
	 * @Bean public WebMvcConfigurer configure() { return new WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "PUT",
	 * "POST", "DELETE", "PATCH", "OPTIONS"); } }; }
	 */

}
