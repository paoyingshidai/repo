package com.michael.j2se.concurrent.fun;

public class UserTask {

	IUserService userService;
	
	public UserTask(IUserService userService) {
		this.userService = userService;
	}
	
	void sayHello(String name) {
		userService.sayHello(name);
	}
	
}
