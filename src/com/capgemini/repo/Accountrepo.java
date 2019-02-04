package com.capgemini.repo;

import com.capgemini.beans.Account;

public interface Accountrepo {
	
	boolean save(Account  account);
	Account searchAccount(int AccountNumber);

}
