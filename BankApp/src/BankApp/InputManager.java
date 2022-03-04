package BankApp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManager {
	
	Scanner myScanner = new Scanner(System.in);
	
	//Takes a String that displays what information to enter
	//Returns user input
	public String GetUserInputAsString(String sMessage)
	{
		String sUserInput = null;
		
		System.out.println(sMessage);
		
		//loop to retry input until we get something we can use
		boolean bInputSuccess = false;
		while(bInputSuccess == false)
		{
			try
			{
				sUserInput = myScanner.nextLine();
				bInputSuccess = true;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Input not Accepted, Please try again: ");
				System.out.println(sMessage);
			}
		}
		
		return sUserInput;
	}
	public int GetUserInputAsInt(String sMessage)
	{
		//String sUserInput = null;
		int nUserInput = 0;
		
		System.out.println(sMessage);
		
		//loop to retry input until we get something we can use
		boolean bInputSuccess = false;
		while(bInputSuccess == false)
		{
			try
			{
				//sUserInput = myScanner.nextLine();
				//nUserInput = Integer.parseInt(sUserInput);
				nUserInput = CheckNegativeInput();
				
				bInputSuccess = true;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Input not Accepted, Please try again: ");
				System.out.println(sMessage);
				
			}
			catch(InputMismatchException e)
			{
				System.out.println("Input not Accepted, Please try again: ");
				System.out.println(sMessage);
			}
			catch (NegativeInputException e)
			{
				System.out.println("Cannot Enter a Negative Number: ");				
			}
		}
		
		return nUserInput;
	}
	public boolean GetUserInputAsBoolean(String sMessage)
	{
		String sUserInput = null;
		boolean bUserInput = false;
		
		System.out.println(sMessage);
		
		//loop to retry input until we get something we can use
		boolean bInputSuccess = false;
		while(bInputSuccess == false)
		{
			try
			{
				sUserInput = myScanner.nextLine();
				
				if(sUserInput.equalsIgnoreCase("YES"))
				{
					bUserInput = true;		
					bInputSuccess = true;
				}
				else if(sUserInput.equalsIgnoreCase("no"))
				{
					bUserInput = false;
					bInputSuccess = true;
				}
				else
					System.out.println("Must answer YES or NO");
				
			}
			catch(InputMismatchException e)
			{
				System.out.println("Input not Accepted, Please try again: ");
				System.out.println(sMessage);
			}
		}
		
		return bUserInput;
	}
	public void Continue()
	{
		System.out.println("\nPress any key to continue");
		myScanner.nextLine();
	}

	public int CheckNegativeInput() throws NegativeInputException
	{
		String sUserInput = null;
		int nUserInput = 0;
		sUserInput = myScanner.nextLine();
		nUserInput = Integer.parseInt(sUserInput);
		if(nUserInput<0)
			throw new NegativeInputException("Input cannot be of a negative value");
		return nUserInput;
	}

}
