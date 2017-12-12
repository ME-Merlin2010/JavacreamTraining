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
import org.javacream.store.api.StoreService;

public class TestOrderActor {

	public static void doOrderTest(BooksService booksService, OrderService orderService) {
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
			
			String myIsbn = "";
			for (Book book : books) {
				myIsbn = book.getIsbn();
				Order order = orderService.order(myIsbn, 1);
				assertTrue(order.getStatus().equals(OrderStatus.OK));
				System.out.println("Order: " + order);
			}
			
			Order order = orderService.order("existiert nicht", 1);
			assertTrue(order.getStatus().equals(OrderStatus.UNAVAILABLE));
			
			order = orderService.order(myIsbn, 2);
			assertTrue(order.getStatus().equals(OrderStatus.PENDING));
			
		} catch (BookException bookException) {
			bookException.printStackTrace();
			fail(bookException.getMessage());
		}

	}

}
