package BankApp;

import java.util.ArrayList;
import java.util.Iterator;
//import java.util.HashMap;
import java.util.Map;

public class CustomerDatabase extends Database<Customer> implements java.io.Serializable{
	
	int nFocusCustomer = 0;
	ArrayList<Integer> pendingAccounts = new ArrayList<Integer>();
	
	//Add a blank user to start a new Customerbase
	public CustomerDatabase()
	{
		//Customer Account number should never be 0
		//using the 0 account to check if a customer is currently "in focus"
		Customer user = new Customer(0,0,null,null,false);
		this.AddAccount(user);
	}
	
	//functionality
	
	public void removeCustomer()
	{
		InputManager inputManager = new InputManager();
		int nAccount = inputManager.GetUserInputAsInt("Which account would you like to remove?\nPlease enter account number:");
		if(!this.dataBase.containsKey(nAccount))
		{
			System.out.println("Account not found!");
		}
		else
		{
			if( inputManager.GetUserInputAsBoolean("Are you sure you want to delete account?"))
			{
				this.dataBase.remove(nAccount);
				removePending(nAccount);
				System.out.println("Account: "+nAccount+" has been removed!");
			}
		}
		inputManager.Continue();
	}
	
	public void removePending(int nAccount)
	{
		Iterator itr = pendingAccounts.iterator();
		while(itr.hasNext())
		{
			if((Integer)itr.next() == nAccount)
				itr.remove();
		}
	}

	public void displayPending()
	{
		System.out.println("|--------Pending Accounts--------|\n");
		
		if(pendingAccounts.size() == 0)
		{
			System.out.println("No accounts pending activation");
			return;
		}
		
		for(int i=0;i<pendingAccounts.size();i++)
		{
			Customer user = this.GetAccount(pendingAccounts.get(i));
			
			//display accounts and they're index+1
			System.out.println((i+1)+ ":\tUsername: "+ user.GetUsername()+"\n");
		}
		System.out.println("\n");
	}
	
	public void pendingActivation()
	{
		
		//Logic for activating account
		displayPending();
		if(pendingAccounts.size() ==0)
			return;
		
		InputManager inputManager = new InputManager();
		int nPendingIndex = inputManager.GetUserInputAsInt("Please select the account:");
		if(nPendingIndex < 1 || nPendingIndex > pendingAccounts.size())
		{
			System.out.println("Index not found!");
		}
		else
		{
			//translate user input into array index
			activateAccount(nPendingIndex -1);
			
		}
		
	}
	
	public void deactivateAccount()
	{
		InputManager inputManager = new InputManager();
		int nAccount = inputManager.GetUserInputAsInt("Which account would you like to deactivate?\nEnter account number:");

		if(!this.dataBase.containsKey(nAccount))
		{
			System.out.println("Account not found!");
			inputManager.Continue();
		}
		else if(!GetAccount(nAccount).GetStatus())
		{
			System.out.println("Account already closed!");
			inputManager.Continue();
		}
		else
		{

			Customer user = GetAccount(nAccount);
			user.SetStatus(false);
			this.pendingAccounts.add(user.GetActNumber());
			System.out.println("Account #: "+ user.GetActNumber()+" Closed!");
			
		}
		
		
	}
	//takes an index in the pendingAccount ArrayList
	public void activateAccount(int nPending)
	{
		Customer user = GetAccount(pendingAccounts.get(nPending));
		user.SetStatus(true);
		pendingAccounts.remove(nPending);
		System.out.println("Account #: "+user.GetActNumber()+" Open!");		
	}
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
			//we should have no account with an account number of 0
			nDestination = 0;
			nDestination = inputManager.GetUserInputAsInt("Enter the Account Number you will transfer to: ");
			
			//check if the destination account number is in our database
			if(!this.dataBase.containsKey(nDestination))
			{
				System.out.println("Account not found!");
			}
			else if(nDestination == nFocusCustomer)
			{
				System.out.println("Cannot transfer to the same account!");				
			}
			else
			{
				//now we find the amount they want to transfer
				
				//Set the transfer amount to a negative for value check
				nAmount = -1;
				nAmount = inputManager.GetUserInputAsInt("How much would you like to transfer? ");
				if(GetFocusCustomer().OverdraftCheck(nAmount))
				{
					//deduct from user account
					GetFocusCustomer().SetBalance(GetFocusCustomer().GetBalance() - nAmount);
					//add to destination account
					this.dataBase.get(nDestination).SetBalance(this.dataBase.get(nDestination).GetBalance()+nAmount);
					
					System.out.println("Transfer Successful");
					
					//Add History to focus account
					String sDisplay = "Customer Transfer to Account:"+nDestination+" Amount: "+nAmount;
					System.out.println(sDisplay);
					GetFocusCustomer().AddHistory(sDisplay);
					
					//Add History to destination account
					sDisplay = "Customer received ammount:"+nAmount+" from account: "+GetFocusCustomer().GetActNumber();
					GetAccount(nDestination).AddHistory(sDisplay);
					
				}

			}
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
		newUser.SetBalance(inputManager.GetUserInputAsInt("How much would you like to deposit?"));
		newUser.AddHistory("Initial Deposit: "+newUser.GetBalance());
		newUser.SetActNumber(this.AssignAccountNumber());
		AddAccount(newUser);
		this.pendingAccounts.add(newUser.GetActNumber());
		return newUser.GetActNumber();
	}
	public int FindAccount(String sUserName)
	{
		try
		{
			for(Map.Entry m :dataBase.entrySet())
			{
				if((int)m.getKey()!=0)
				{
					Customer user = (Customer)m.getValue();
					
					if(user.GetUsername().equalsIgnoreCase(sUserName))
					{
						return user.GetActNumber();
					}
				}
			}
		}
		catch(NullPointerException e)
		{
			return 0;
		}
		return 0;
	}

	public Customer GetFirst()
	{
		return (Customer)dataBase.entrySet().iterator().next().getValue();
	}
	public boolean ChangeFocusCustomer()
	{
		InputManager inputManager = new InputManager();
		int nAccount = 0;
		boolean bSuccess = false;

		nAccount = inputManager.GetUserInputAsInt("Enter an Account Number:");
		if(!this.dataBase.containsKey(nAccount) || nAccount==0)
		{
			System.out.println("That Account does not exist!");
			nAccount = 0;
		}
		else
		{
			nFocusCustomer = nAccount;
			bSuccess = true;
		}
		return bSuccess;

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
			if((int)m.getKey()!=0)
			{
				Customer user = (Customer)m.getValue();
				
				if(user.GetUsername().equalsIgnoreCase(sName))
				{
					return false;
				}
			}
		}
		return true;
		
	}


}
