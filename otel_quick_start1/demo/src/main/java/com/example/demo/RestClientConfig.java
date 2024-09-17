package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

   @Autowired
   RestTemplateInterceptor restTemplateHeaderModifierInterceptor;

   @Bean
   public RestTemplate restTemplate() {
      RestTemplate restTemplate = new RestTemplate();

      restTemplate.getInterceptors().add(restTemplateHeaderModifierInterceptor);

      return restTemplate;
   }
}