package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.BankAccount;

public class BankDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();

	public void saveAccount(BankAccount bankAccount) {
//		EntityTransaction entityTransaction = entityManager.getTransaction();

		// it is used to ready the data to upload
		entityTransaction.begin();

		// it is used to carry the data eclipse to db
		entityManager.persist(bankAccount);

		// it is used to push the data into db
		entityTransaction.commit();

		System.out.println("Data Saved Successfully");
	}

	public List<BankAccount> fetchAll() {
		List<BankAccount> acc_list= entityManager.createQuery("Select x from BankAccount x").getResultList();
		return acc_list;
	}

	public BankAccount fetch(long acc_no) {
		BankAccount account_details= entityManager.find(BankAccount.class, acc_no);	
		return account_details;
	}

	public void update(BankAccount acc_details) {
		// it is used to ready the data to upload
		entityTransaction.begin();

		// it is used to update the data eclipse to db
		entityManager.merge(acc_details);

		// it is used to push the data into db
		entityTransaction.commit();

		System.out.println("Data updated Successfully");
	}
}	
