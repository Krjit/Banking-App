package dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class BankTransaction {
	@Id
	@SequenceGenerator(initialValue = 0001, allocationSize = 1, sequenceName = "Transacid", name = "Transacid")
	@GeneratedValue(generator = "Transacid")
	int tid;
	
	double dAmount;
	double wAmount;
	double balance;
	LocalDateTime data_time;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public double getdAmount() {
		return dAmount;
	}
	public void setdAmount(double dAmount) {
		this.dAmount = dAmount;
	}
	public double getwAmount() {
		return wAmount;
	}
	public void setwAmount(double wAmount) {
		this.wAmount = wAmount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public LocalDateTime getData_time() {
		return data_time;
	}
	public void setData_time(LocalDateTime data_time) {
		this.data_time = data_time;
	}
}
