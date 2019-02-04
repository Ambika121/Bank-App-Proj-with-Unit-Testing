package com.capgemini.beans;

public class Account {
	
	int Accountnumber;
	int Amount;
	
	public Account(int accountnumber, int amount) {
		super();
		Accountnumber = accountnumber;
		Amount = amount;
	}

	public int getAccountnumber() {
		return Accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		Accountnumber = accountnumber;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Accountnumber;
		result = prime * result + Amount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Accountnumber != other.Accountnumber)
			return false;
		if (Amount != other.Amount)
			return false;
		return true;
	}
	
	
}
