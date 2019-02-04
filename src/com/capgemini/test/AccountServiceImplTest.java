package com.capgemini.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.capgemini.beans.Account;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientOpeningBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.repo.Accountrepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountserviceImpl;

public class AccountServiceImplTest {

	@Mock
	Accountrepo accountRepo;
	AccountService accountservice;
	
	@Before
	public void setup() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		accountservice = new AccountserviceImpl(accountRepo);
	}
	
	/*
	 * create account
	 * 1.when the amount is less than 500 then system should throw exception
	 * 2.when the valid info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exception.InsufficientOpeningBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientOpeningBalanceException
	{
		accountservice.createAccount(101, 400);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientOpeningBalanceException
	{
		Account account = new Account();
		account.setAccountnumber(101);
		account.setAmount(5000);
		when(accountRepo.save(account)).thenReturn(true);
		assertEquals(account, accountservice.createAccount(101, 5000));
	}
	
	/*
	 * Deposit in Account
	 * 1. When account number is not valid, throw exception
	 * 2. when account number is valid, deposit.
	 */
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenAccountIsNotValidThanThrowException() throws InvalidAccountNumberException
	{
		accountservice.deposit(105, 2000);
	}
	
	@Test
	public void whenAccountNumberIsValidThanDeposit() throws InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountnumber(101);
		account.setAmount(5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		assertEquals(account.getAmount() + 500, accountservice.deposit(101, 500));
	}
	
	/*
	 * Withdraw from Account
	 * 1. When account number is not valid, throw exception
	 * 2. When amount is not sufficient to complete withdraw, throw exception
	 * 3. when request for valid transaction, it should be done.
	 */
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenInvalidAccountNumberThrowException() throws InsufficientBalanceException, InvalidAccountNumberException
	{
		accountservice.withdraw(101, 5000);
	}
	
	@Test(expected = com.capgemini.exception.InsufficientBalanceException.class)
	public void whenAmountIsNotSufficientThrowException() throws InsufficientBalanceException, InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountnumber(101);
		account.setAmount(5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountservice.withdraw(101,6000);
	}
	
	@Test
	public void ValidInfoIsPassedWithdrawComplete() throws InsufficientBalanceException, InvalidAccountNumberException
	{
		Account account = new Account();
		account.setAccountnumber(101);
		account.setAmount(5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		assertEquals(account.getAmount() - 500, accountservice.withdraw(101, 500));
	}

}
