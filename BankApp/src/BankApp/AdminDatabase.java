package BankApp;

//import java.util.HashMap;
import java.util.Map;

public class AdminDatabase extends Database<Admin> implements java.io.Serializable{
	

	public AdminDatabase()
	{
		//Should always have a master manager account to add others
		Admin user =new Admin("Mgr1","Mgr",AssignAccountNumber(), true);
		this.AddAccount(user);
		
	}
	public int Register()
	{
		Admin newAdmin = new Admin();
		InputManager inputManager = new InputManager();
		
//		do
//		{
//			newAdmin.SetUsername(inputManager.GetUserInputAsString("Please Eneter your Username: "));
//		}
//		while(!NameCheck(newAdmin.GetUsername()));
		
		String sUsername = "";
		sUsername = inputManager.GetUserInputAsString("Please Enter your Username: ");
		
		//check if name is unique, if not, return and do not create
		if(!NameCheck(sUsername))
		{
			System.out.println("That Username is already taken!");
			return 0;
		}
		
		newAdmin.SetUsername(sUsername);
		
		
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

	public void MakeManager()
	{

		InputManager inputManager = new InputManager();
		String sAccount = null;
		sAccount = inputManager.GetUserInputAsString("Enter an Account Name:");

		int nAccountNumber = this.FindAccount(sAccount);
			if(nAccountNumber==0)
			{
				System.out.println("That Account does not exist!");
			}
			else if(this.GetAccount(nAccountNumber).GetManagerStatus())
			{
				System.out.println("This account is already a manager!");
			}
			else
			{
				 this.GetAccount(nAccountNumber).SetManagerStatus(true);
				 System.out.println(this.GetAccount(nAccountNumber).GetUsername()+" has been given manager access");
			}
			
			
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
