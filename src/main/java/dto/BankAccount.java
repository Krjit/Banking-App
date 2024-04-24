package dto;

import java.util.List;

import javax.persistence.*;

@Entity
public class BankAccount{
	@Id
	@SequenceGenerator(initialValue=4042597, allocationSize = 1, sequenceName = "acc_no", name="acc_no")
	@GeneratedValue(generator = "acc_no")
	long acc_no;
	
	String acc_type;
	
	double acc_limit;
	
	boolean status;
	
	double amount;
	
	@ManyToOne
	Customer customer;
	
	@OneToMany (cascade=CascadeType.ALL) //It used to save the data inside database without using dao package's class which consist begin, persist and commit
	List<BankTransaction> listTransaction;
	
	public List<BankTransaction> getListTransaction() {
		return listTransaction;
	}

	public void setListTransaction(List<BankTransaction> listTransaction) {
		this.listTransaction = listTransaction;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(long acc_no) {
		this.acc_no = acc_no;
	}

	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	public double getAcc_limit() {
		return acc_limit;
	}

	public void setAcc_limit(double acc_limit) {
		this.acc_limit = acc_limit;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}