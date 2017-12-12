package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.impl.MapBooksService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UntypicalSpringTest {

	ClassPathXmlApplicationContext context;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("application.xml");
	}
	
	@Test
	public void testBusinessObjects() {
		MapBooksService mapBooksService = context.getBean(MapBooksService.class); 
		TestActor.doTest(mapBooksService);
	}
		

}
