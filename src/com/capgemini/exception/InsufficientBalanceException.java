package com.capgemini.exception;

public class InsufficientBalanceException extends Exception {
	
	@Override
	public String getMessage()
	{
		return("Balance is not sufficient to Withdraw");
	}

}
