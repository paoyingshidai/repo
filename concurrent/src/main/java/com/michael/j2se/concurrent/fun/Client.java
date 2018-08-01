package com.michael.j2se.concurrent.fun;

public class Client {

	public static void main(String[] args) {
		UserTask task = new UserTask(name -> {System.out.println("hello : " + name);});
		task.sayHello("Michael");
	}
	
}
