package BankApp;

public class NegativeInputException extends RuntimeException{

	public NegativeInputException(String errorMessage)
	{
		super(errorMessage);
	}
}
