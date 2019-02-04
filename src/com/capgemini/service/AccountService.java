package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientOpeningBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;

public interface AccountService {

	Account createAccount(int accountNumber, int amount) throws InsufficientOpeningBalanceException;

	int deposit(int accountNumber, int amount) throws InvalidAccountNumberException;

	int withdraw(int accountNumber, int amount) throws InsufficientBalanceException, InvalidAccountNumberException;

}