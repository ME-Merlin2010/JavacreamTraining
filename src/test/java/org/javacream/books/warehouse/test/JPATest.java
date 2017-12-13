package org.javacream.books.warehouse.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto training@rainer-sawitzki.de
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class JPATest {


	@PersistenceContext private EntityManager entityManager;

	
	@Test @Transactional public void testEntityManager(){
		Assert.assertNotNull(entityManager);
		entityManager.createNativeQuery("insert into messages values ('JPA')").executeUpdate();
	}

	// @Autowired private EntityManagerFactory emf;
	// @Autowired private EntityManager em;

	@PersistenceContext(unitName = "JPA")
	private EntityManager em;

	@SuppressWarnings("rawtypes")
	@Test
	@Transactional
	public void testJpa() {
//		EntityTransaction transaction = em.getTransaction();
//		transaction.begin();
//		try {
			// Assert.assertNotNull(emf);
			Assert.assertNotNull(em);
			Query query = em.createNativeQuery("select message from messages");
			List list = query.getResultList();
			for (Object element : list) {
				System.out.println(element);
			}

			Query insertQuery = em.createNativeQuery("insert into messages values (:newMessage)");
			insertQuery.setParameter("newMessage", "Hello from JPA!");
			insertQuery.executeUpdate();
//			transaction.commit();
//		} catch (RuntimeException e) {
//			transaction.rollback();
//		}
	}

}
