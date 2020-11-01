package com.in28minutes.springboot.basics.springbootin10steps.helloworld;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.basics.springbootin10steps.Book;

@RestController
public class BooksController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/books")
	public List<Book> getAllBooks()
	{
		return Arrays.asList(
				new Book(1l, "astering Spring 5.5", "Ranga Karanam"));
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello world");
	}
	
	@GetMapping("/hello-world-internationalized-worse-impl")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale)
	{
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
	//Better implementation
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized2()
	{
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello world %s", name));
	}
}
