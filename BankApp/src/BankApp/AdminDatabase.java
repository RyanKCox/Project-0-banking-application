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
		System.out.println(newAdmin.GetUsername()+" has been added!");
		return newAdmin.GetActNumber();
		
	}
	public void AddAccount(Admin newAdmin)
	{
		dataBase.put(newAdmin.GetActNumber(), newAdmin);
	}

	public void MakeManager(int nAdmin)
	{
		 this.GetAccount(nAdmin).SetManagerStatus(true);
		 System.out.println(this.GetAccount(nAdmin).GetUsername()+" has been given manager access");
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
			Admin user = (Admin)m.getValue();
			
			if(user.GetUsername().equalsIgnoreCase(sName))
			{
				return false;
			}
		}
		return true;
		
	}
}
