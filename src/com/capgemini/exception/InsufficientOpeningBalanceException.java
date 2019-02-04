package com.capgemini.exception;

public class InsufficientOpeningBalanceException extends Exception {
	
	@Override
	public String getMessage()
	{
		return("Opening Balance should be greater than 500");
	}

}
