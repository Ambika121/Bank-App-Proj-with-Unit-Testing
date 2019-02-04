package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientOpeningBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.repo.Accountrepo;

public class AccountserviceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	
	Accountrepo accountrepo;
	
	public AccountserviceImpl(Accountrepo accountrepo) {
		super();
		this.accountrepo = accountrepo;
	}

    /* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
    @Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientOpeningBalanceException
	{
		if(amount<500)
		{
			throw new InsufficientOpeningBalanceException();
		}
		
		Account account = new Account(accountNumber, amount);
		account.setAccountnumber(accountNumber);
		account.setAmount(amount);
		return account;
	}
    
    /* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#deposit(com.capgemini.beans.Account, int)
	 */
    @Override
	public int deposit(int accountNumber, int amount) throws InvalidAccountNumberException
    {
    	Account account = accountrepo.searchAccount(accountNumber);
    	
    	if(account == null)
    	{
    		throw new InvalidAccountNumberException();
    	}
    	account.setAmount(account.getAmount() + amount);
    	accountrepo.save(account);
    	return account.getAmount();
    }
    
    /* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#withdraw(com.capgemini.beans.Account, int)
	 */
    @Override
	public int withdraw(int accountNumber, int amount) throws InsufficientBalanceException, InvalidAccountNumberException
    {
    	Account account = accountrepo.searchAccount(accountNumber);
    	
    	if(account == null)
    	{
    		throw new InvalidAccountNumberException();
    	}
    	
    	if(account.getAmount() - amount < 0)
    	{
    		throw new InsufficientBalanceException();
    	}
    	
    	account.setAmount(account.getAmount() - amount);
    	accountrepo.save(account);
    	return account.getAmount();
    }

}
