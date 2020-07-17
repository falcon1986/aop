package com.wwq;

import org.junit.Test;

public class ProxyTest {
	
	@Test
	public void proxyTest() {
		System.out.println("===============proxyTest===============");
		
		Dog dog = DogProxy.getProxy(new Dog());
		
		System.out.println(dog.eat("apple"));
		dog.run();
	}
}
