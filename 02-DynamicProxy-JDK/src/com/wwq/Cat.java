package com.wwq;

public class Cat implements Animal {

	@Override
	public String eat(String food) {
		System.out.println("cat eat " + food);
		return "cat eat " + food + " end...";
	}

	@Override
	public String run() {
		System.out.println("cat running... ");
		return "cat running... ";
	}
}
