package BankApp;

import java.util.HashMap;
import java.util.Map;

public class CustomerDatabase extends Database<Customer> implements java.io.Serializable{
	
	int nFocusCustomer = 0;
	
	//Add a blank user to start a new Customerbase
	public CustomerDatabase()
	{
		Customer user = new Customer(0,this.AssignAccountNumber(),"Ryan","Cox",false);
		this.AddAccount(user);
		nFocusCustomer = user.GetActNumber();
		//Main.user = user;
	}
	
	//functionality
	public void Transfer()
	{
		
		if(!GetFocusCustomer().CheckStatus())
		{
			return;
		}
		InputManager inputManager = new InputManager();
		
		//first get the destination account
		int nDestination ;
		int nAmount;
		do
		{
			//we should have no account with an account number of 0
			nDestination = 0;
			nDestination = inputManager.GetUserInputAsInt("Enter the Account Number you will transfer to: ");
			
			//check if the destination account number is in our database
			if(!this.dataBase.containsKey(nDestination))
			{
				System.out.println("Account not found!");
			}
			else
			{
				//now we find the amount they want to transfer
				
				//Set the transfer amount to a negative for value check
				nAmount = -1;
				do
				{
					nAmount = inputManager.GetUserInputAsInt("How much would you like to transfer? ");
					if(GetFocusCustomer().OverdraftCheck(nAmount))
					{
						//deduct from user account
						GetFocusCustomer().SetBalance(GetFocusCustomer().GetBalance() - nAmount);
						//add to destination account
						this.dataBase.get(nDestination).SetBalance(this.dataBase.get(nDestination).GetBalance()+nAmount);
						
						System.out.println("Transfer Successful");
						
						String sDisplay = "Customer Transfer to Account:"+nDestination+" Amount: "+nAmount;
						System.out.println(sDisplay);
						GetFocusCustomer().AddHistory(sDisplay);
						
						//Main.user.AddHistory("Customer Transfer to Account:"+nDestination+" Amount: "+nAmount);
					}
					else
					{
						nAmount = -1;
					}
				}
				while(nAmount < 0);
			}
		}
		while(!this.dataBase.containsKey(nDestination));
	}	
	public void AddAccount(Customer user)
	{
		dataBase.put(user.GetActNumber(), user);
	}

	public void displayUserbase()
	{
		for(Map.Entry m : dataBase.entrySet())
		{
			System.out.println('\n');
			Customer user = (Customer) m.getValue();
			user.DisplayCustomer();
		}
	}
	public int Register()
	{
		Customer newUser = new Customer();
		InputManager inputManager = new InputManager();
		
		do
		{
			newUser.SetUsername(inputManager.GetUserInputAsString("Please Eneter your Username: "));
		}
		while(!NameCheck(newUser.GetUsername()));
		
		newUser.SetPassword(inputManager.GetUserInputAsString("Please Eneter your Password: "));
		newUser.SetType(inputManager.GetUserInputAsBoolean("Is this a joint account? \nPlease enter Yes or No"));
		//newUser.Deposit();
		newUser.SetBalance(inputManager.GetUserInputAsInt("How much would you like to deposit?"));
		newUser.AddHistory("Initial Deposit: "+newUser.GetBalance());
		newUser.SetActNumber(this.AssignAccountNumber());
		AddAccount(newUser);
		return newUser.GetActNumber();
	}
	public int FindAccount(String sUserName)
	{
		for(Map.Entry m :dataBase.entrySet())
		{
			Customer user = (Customer)m.getValue();
			
			if(user.GetUsername().equalsIgnoreCase(sUserName))
			{
				return user.GetActNumber();
			}
		}
		return 0;
	}

	public Customer GetFirst()
	{
		return (Customer)dataBase.entrySet().iterator().next().getValue();
	}
	public void ChangeFocusCustomer()
	{
		InputManager inputManager = new InputManager();
		int nAccount = 0;
		do
		{
			nAccount = inputManager.GetUserInputAsInt("Enter an Account Number:");
			if(!this.dataBase.containsKey(nAccount))
			{
				System.out.println("That Account does not exist!");
				nAccount = 0;
			}
			else
			{
				nFocusCustomer = nAccount;
				//Main.user = GetAccount(nAccount);
			}
		}
		while(nAccount <=0);
	}

	public Customer GetFocusCustomer()
	{
		return this.GetAccount(nFocusCustomer);
	}
	
	//Checks the password for the account given, returns false if failed
	public boolean CheckPassword(int nAccount, String sPass)
	{
		if(dataBase.get(nAccount).GetPassword().equals(sPass))
		{
			return true;
		}
		return false;
	}
	
	//Checks for unique username, returns true if unique
	public boolean NameCheck(String sName)
	{
		for(Map.Entry m :dataBase.entrySet())
		{
			Customer user = (Customer)m.getValue();
			
			if(user.GetUsername().equalsIgnoreCase(sName))
			{
				return false;
			}
		}
		return true;
		
	}


}
	//Returns the account number if Successful, 0 if not
//	public int Login()
//	{
//		InputManager inputManager = new InputManager();
//		int nAccount = 0;
//		nAccount = FindAccount(inputManager.GetUserInputAsString("Enter Username: "));
//		if(nAccount == 0)
//		{
//			System.out.println("User not found!");
//		}
//		else
//		{
//			if(!CheckPassword(nAccount,inputManager.GetUserInputAsString("Enter Password: ")))
//			{
//				System.out.println("Password Incorrect!");
//			}
//			else
//				return nAccount;
//		}
//		return nAccount;
//		
//	}
//public int AssignAccountNumber()
//{
//	int nKey = 0;
//	for(int i = 1; nKey == 0; i++)
//	{
//		if(!userBase.containsKey(i))
//			nKey=i;
//	}
//	return nKey;
//}	
//public Customer GetUser(int nAccount)
//{
//	return userBase.get(nAccount);
//}