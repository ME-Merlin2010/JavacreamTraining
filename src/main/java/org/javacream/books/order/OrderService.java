package org.javacream.books.order;

import java.util.List;

public interface OrderService {

	Order order(String isbn, int number);
	Order findById(long id);
	List<Order> findOrdersByIsbn(String isbn);
	void update(Order order);
	void delete(Order order);

}
