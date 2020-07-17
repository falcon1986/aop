package com.wwq;

public class Dog implements Animal {

	@Override
	public String eat(String food) {
		System.out.println("dog eat " + food);
		return "dog eat " + food + " end...";
	}

	@Override
	public String run() {
		System.out.println("dog running... ");
		return "dog running... ";
	}
}
