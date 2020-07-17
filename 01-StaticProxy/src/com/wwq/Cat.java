package com.wwq;

public class Cat implements Animal {

	@Override
	public String eat(String food) {
//		System.out.println("---eat|proxy|before---");
		System.out.println("cat eat " + food);
//		System.out.println("---eat|proxy|after---");
		return "cat eat " + food + " end...";
	}

	@Override
	public String run() {
//		System.out.println("---run|proxy|before---");
		System.out.println("cat running... ");
//		System.out.println("---run|proxy|after---");
		return "cat running... ";
	}
}
