package com.wwq;

public class Dog implements Animal {

	@Override
	public String eat(String food) {
//		System.out.println("---eat|proxy|before---");
		System.out.println("dog eat " + food);
//		System.out.println("---eat|proxy|after---");
		return "dog eat " + food + " end...";
	}

	@Override
	public String run() {
//		System.out.println("---run|proxy|before---");
		System.out.println("dog running... ");
//		System.out.println("---run|proxy|after---");
		return "dog running... ";
	}
}
