package BankApp;

import java.util.HashMap;
import java.util.Map;

public class AdminDatabase extends Database<Admin> implements java.io.Serializable{
	
	//Map<Integer,Admin> adminBase = new HashMap<Integer,Admin>();

	public AdminDatabase()
	{
		Admin user =new Admin("Ryan","Cox",AssignAccountNumber(), true);
		this.AddAccount(user);
		//Main.admin = user;
		
		//Set up an employee
		Admin employee = new Admin("Test","Test",AssignAccountNumber(),false);
		this.AddAccount(employee);
	}
	public int Register()
	{
		Admin newAdmin = new Admin();
		InputManager inputManager = new InputManager();
		
		do
		{
			newAdmin.SetUsername(inputManager.GetUserInputAsString("Please Eneter your Username: "));
		}
		while(!NameCheck(newAdmin.GetUsername()));
		
		newAdmin.SetPassword(inputManager.GetUserInputAsString("Please Eneter your Password: "));

		newAdmin.SetActNumber(this.AssignAccountNumber());
		AddAccount(newAdmin);
		return newAdmin.GetActNumber();
		
	}
	public void AddAccount(Admin newAdmin)
	{
		dataBase.put(newAdmin.GetActNumber(), newAdmin);
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

	public int FindAccount(String sAdminName)
	{
		for(Map.Entry m :dataBase.entrySet())
		{
			Admin admin = (Admin)m.getValue();
			
			if(admin.GetUsername().equalsIgnoreCase(sAdminName))
			{
				return admin.GetActNumber();
			}
		}
		return 0;
	}

	//Returns true for a unique name
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

//public int Login()
//{
//	InputManager inputManager = new InputManager();
//	int nAccount = 0;
//	nAccount = FindAccount(inputManager.GetUserInputAsString("Enter Username: "));
//	if(nAccount == 0)
//	{
//		System.out.println("User not found!");
//	}
//	else
//	{
//		if(!CheckPassword(nAccount,inputManager.GetUserInputAsString("Enter Password: ")))
//		{
//			System.out.println("Password Incorrect!");
//		}
//		else
//			return nAccount;
//	}
//	return nAccount;
//}

//public int AssignAccountNumber()
//{
//	int nKey = 0;
//	for(int i = 1; nKey == 0; i++)
//	{
//		if(!adminBase.containsKey(i))
//			nKey=i;
//	}
//	return nKey;
//}	

//public Admin GetAccount(int nAccount)
//{
//	return adminBase.get(nAccount);
//}