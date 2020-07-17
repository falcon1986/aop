package com.wwq;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnimalProxy {

	public static Animal getAnimal(Animal animal) {
		return (Animal)Proxy.newProxyInstance(animal.getClass().getClassLoader(), animal.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("---" + method.getName() + "|proxy|before---");
				if(args != null) {
					for(int i = 0; i < args.length; i++) {
						System.out.println("--arg-ori:" + args[i]);
						args[i] += "(proxy)";
					}
				}
				Object res = method.invoke(animal, args);
				System.out.println("---" + method.getName() + "|proxy|after---");
				return res.toString() + "(proxy)";
			}
		});
	}
}
