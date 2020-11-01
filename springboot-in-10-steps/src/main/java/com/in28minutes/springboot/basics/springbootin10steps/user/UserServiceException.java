package com.in28minutes.springboot.basics.springbootin10steps.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class UserServiceException extends RuntimeException  {

	public UserServiceException(Exception ex) {
		super(getStackTraceString(ex));
		// TODO Auto-generated constructor stub
	}

	private static String getStackTraceString(Exception ex) {
		String stackTraceString = ex.toString();
		StackTraceElement[] stackTraceElements = ex.getStackTrace();
		for (StackTraceElement stackTraceElement: stackTraceElements) {
			stackTraceString = stackTraceString + System.lineSeparator() + stackTraceElement.toString();
		}
		return stackTraceString;
	}
}
