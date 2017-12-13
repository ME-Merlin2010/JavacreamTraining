package org.javacream.books.warehouse.test;

import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.JpaBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.JdbcStoreService;
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
public class AspectjTest {

	@Autowired
	private StoreService storeService;

	@Autowired
	private BooksService booksService;

	@Test
	public void testSpring() {
		TestActor.doTest(booksService);
	}

	@Test // aopAlliance
	public void testTypes() {
		System.out.println(booksService.getClass().getName());
		System.out.println(storeService.getClass().getName());
	}

	// @Test aopalliance
	public void testCastWithoutProxyOk() {
		@SuppressWarnings("unused")
		JpaBooksService mapBooksService = (JpaBooksService) booksService;
	}

	// @Test(expected=ClassCastException.class) aopalliance
	public void testCastWithProxyMustFail() {
		@SuppressWarnings("unused")
		JdbcStoreService jdbcStoreService = (JdbcStoreService) storeService;
	}

}
