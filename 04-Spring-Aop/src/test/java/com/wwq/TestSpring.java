package com.wwq;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	
	@Test
	public void proxyTest() {
		System.out.println("===============proxyTest===============");
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Animal cat = cxt.getBean("cat", Animal.class);
		Dog dog = cxt.getBean("dog", Dog.class);
		
		System.out.println(cat.eat("apple"));
		cat.run();
		
		System.out.println("--------------------------------------------------------------------------");
		
		System.out.println(dog.eat("apple"));
		dog.run();
		
		cxt.close();
	}

}
