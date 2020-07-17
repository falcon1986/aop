package com.wwq;

import org.springframework.stereotype.Component;

@Component
public class Cat implements Animal {

	//连接点
	public String eat(String food) {
		System.out.println("cat eat " + food);
		return "cat eat " + food + " end...";
	}

	//连接点
	public String run() {
		System.out.println("cat running... ");
		return "cat running... ";
	}
}
