package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.api.BooksService;
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
		BooksService mapBooksService = context.getBean(BooksService.class); 
		TestActor.doTest(mapBooksService);
	}
		

}
