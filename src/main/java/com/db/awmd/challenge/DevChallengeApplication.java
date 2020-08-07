package com.db.awmd.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.db.awmd.challenge.service.NotificationServiceImpl;

@SpringBootApplication
public class DevChallengeApplication {

	
	
  public static void main(String[] args) {
    SpringApplication.run(DevChallengeApplication.class, args);
  }
  
  @Bean
  public Logger logger(InjectionPoint injectionPoint) {
      Class<?> targetClass = injectionPoint.getMember().getDeclaringClass();
      return LoggerFactory.getLogger(targetClass);
  }
  
  @Bean
  public NotificationServiceImpl notificationService(){
	  return new NotificationServiceImpl();
  }
}
