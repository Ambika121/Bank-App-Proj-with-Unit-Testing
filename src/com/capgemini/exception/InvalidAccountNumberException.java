package com.capgemini.exception;

public class InvalidAccountNumberException extends Exception {
	
	@Override
	public String getMessage()
	{
		return("Account Number is not Valid");
	}

}
