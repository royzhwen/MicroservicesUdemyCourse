package com.in28minutes.springboot.basics.springbootin10steps;

import java.util.Locale;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringbootIn10StepsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringbootIn10StepsApplication.class, args);
		
//		for (String name: applicationContext.getBeanDefinitionNames())
//		{
//			System.out.println("~~~" + name);
//		}
		
	}
	
	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
		//SessionLocaleResolver localeResolver = new SessionLocaleResolver(); //Used with worse implementation for internationalization
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver(); //Used with better implementation for internationalization
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	//Comment this out if better implentation for internationalization is used, otherwise uncomment
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}
}
