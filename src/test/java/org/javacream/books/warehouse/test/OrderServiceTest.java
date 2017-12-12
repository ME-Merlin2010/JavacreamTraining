package org.javacream.books.warehouse.test;

import org.javacream.books.order.OrderService;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto training@rainer-sawitzki.de
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OrderServiceTest {

	@Autowired
	private BooksService booksService;

	@Autowired
	private OrderService orderService;

	@Test
	public void testSpring() {
		TestOrderActor.doOrderTest(booksService, orderService);
	}

}
