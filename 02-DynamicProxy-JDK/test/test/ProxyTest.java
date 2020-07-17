package test;

import org.junit.Test;

import com.wwq.Animal;
import com.wwq.AnimalProxy;
import com.wwq.Cat;
import com.wwq.Dog;

public class ProxyTest {
	
	@Test
	public void proxyTest() {
		System.out.println("===============proxyTest===============");
		
		Animal cat = AnimalProxy.getAnimal(new Cat());
		Animal dog = AnimalProxy.getAnimal(new Dog());
		
		System.out.println(cat.eat("apple"));
		cat.run();
		
		System.out.println("-------------------------------------");
		
		System.out.println(dog.eat("apple"));
		dog.run();
	}
}
