package com.wwq;

public class AnimalProxy implements Animal {
	
	private Animal animal;
	
	public AnimalProxy(Animal animal) {
		this.animal = animal;
	}

	@Override
	public String eat(String food) {
		System.out.println("---eat|proxy|before---");
		food = food + "(proxy)";
		String res = animal.eat(food);
		res = res + "(proxy)";
		System.out.println("---eat|proxy|after---");
		return res;
	}

	@Override
	public String run() {
		System.out.println("---run|proxy|before---");
		String res = animal.run();
		System.out.println("---run|proxy|after---");
		return res;
	}

	
}
