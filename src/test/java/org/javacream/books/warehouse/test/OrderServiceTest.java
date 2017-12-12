package org.javacream.books.warehouse.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.javacream.books.order.Order;
import org.javacream.books.order.OrderService;
import org.javacream.books.order.OrderStatus;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
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
		try {
			Collection<Book> books = booksService.findAllBooks();
			final int startSize = books.size();
			String j2eeTitle = "Spring";
			String isbn = booksService.newBook(j2eeTitle);
			books = booksService.findAllBooks();
			final int endSize = books.size();
			assertTrue(endSize == startSize + 1);
			assertNotNull(isbn);
			assertNotNull(booksService.findBookByIsbn(isbn));
			
			
			for (Book book : books) {
				String myIsbn = book.getIsbn();
				assertTrue(isbn.equals(myIsbn));
				Order order = orderService.order(myIsbn, 0);
				assertTrue(order.getStatus().equals(OrderStatus.OK));
				System.out.println("Order: " + order);
			}
			
			Order order = orderService.order("existiert nicht", 1);
			assertTrue(order.getStatus().equals(OrderStatus.UNAVAILABLE));
			
			order = orderService.order(isbn, 500);
			assertTrue(order.getStatus().equals(OrderStatus.PENDING));
			
		} catch (BookException bookException) {
			bookException.printStackTrace();
			fail(bookException.getMessage());
		}
	}

}
