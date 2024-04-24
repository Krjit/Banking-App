package dao;

import java.util.List;

import javax.persistence.*;

import dto.Customer;

public class CustDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();

	public void insert(Customer customer) {
//		EntityTransaction entityTransaction = entityManager.getTransaction();

		// it is used to ready the data to upload
		entityTransaction.begin();

		// it is used to carry the data eclipse to db
		entityManager.persist(customer);

		// it is used to push the data into db
		entityTransaction.commit();

		System.out.println("Data Saved Successfully");
	}

	public List<Customer> checkEmail(String email) {
		@SuppressWarnings("unchecked")
		List<Customer> list= entityManager.createQuery("select z from Customer z where email=?1").setParameter(1, email).getResultList();
		return list;
	}
	
	public List<Customer> checkMobile(long mobile) {
		@SuppressWarnings("unchecked")
		List<Customer> list= entityManager.createQuery("select z from Customer z where mob=?1").setParameter(1, mobile).getResultList();
		return list;
	}
	
	public Customer login(int custid) {
		Customer cInfo=entityManager.find(Customer.class, custid);
		return cInfo;
	}

	public void update(Customer customer) {
//		EntityTransaction entityTransaction = entityManager.getTransaction();

		// it is used to ready the data to upload
		entityTransaction.begin();

		// it is used to carry the data eclipse to db
		entityManager.merge(customer);

		// it is used to push the data into db
		entityTransaction.commit();

		System.out.println("Data updated Successfully");
		
	}

}
