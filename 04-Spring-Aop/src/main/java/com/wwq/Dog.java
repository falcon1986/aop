package com.wwq;

import org.springframework.stereotype.Component;

@Component
public class Dog {

	//连接点
	public String eat(String food) {
		System.out.println("dog eat " + food);
		return "dog eat " + food + " end...";
	}

	//连接点
	public String run() {
		System.out.println("dog running... ");
		return "dog running... ";
	}

	
}