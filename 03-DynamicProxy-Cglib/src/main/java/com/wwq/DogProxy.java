package com.wwq;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DogProxy {

	public static Dog getProxy(final Dog dog) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(dog.getClass());
		enhancer.setCallback(new MethodInterceptor() {

			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("---" + method.getName() + "|proxy|before---");
				if(args != null) {
					for(int i = 0; i < args.length; i++) {
						System.out.println("--arg-ori:" + args[i]);
						args[i] = args[i].toString() + "(proxy)";
					}
				}
				Object res = method.invoke(dog, args);
				System.out.println("---" + method.getName() + "|proxy|after---");
				return res.toString() + "(proxy)";
			}
		});
		
		return (Dog) enhancer.create();
	}
}
