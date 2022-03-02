package BankApp;


public class Admin implements java.io.Serializable {
	
	private String sUsername;
	private String sPassword;
	private int nAccountNumber;
	private boolean bIsManager;
	
	//Constructors
	public Admin()
	{
		sUsername = "";
		sPassword = "";
		nAccountNumber = 0;
		bIsManager = false;
	}
	public Admin(String sUser, String sPass, int nAcct, boolean bManager)
	{
		sUsername = sUser;
		sPassword = sPass;
		nAccountNumber = nAcct;
		bIsManager = bManager;
	}
	
	public boolean isManager()
	{
		if(bIsManager == false)
		{
			System.out.print("You do not have this access\n");
			InputManager inputManager = new InputManager();
			inputManager.Continue();
		}
		return bIsManager;
	}
	
	//Functionality
	public void ApproveCustomer()
	{
		//Main.user.SetStatus(true);
		Main.databaseManager.GetFocusCustomer().SetStatus(true);
	}
	public void CancelCustomer()
	{
		//Main.user.SetStatus(false);	
		Main.databaseManager.GetFocusCustomer().SetStatus(false);	
	}	
	public void ToggleStatus()
	{
		//Main.user.SetStatus(!Main.user.GetStatus());
		Main.databaseManager.GetFocusCustomer().SetStatus(!Main.databaseManager.GetFocusCustomer().GetStatus());
	}
	public void ChangeCustomer()
	{
		InputManager inputManager = new InputManager();
		boolean bResult = false;
		int nAccount = -1;
		
		do
		{
			//Get the account number
			nAccount = inputManager.GetUserInputAsInt("Enter the accout number: ");
			
			//if the account exists, focus the account and return true
			//if(Main.userBase.dataBase.containsKey(nAccount))
			if(Main.databaseManager.GetUserBase().dataBase.containsKey(nAccount))
			{
				Main.databaseManager.SetFocusCustomer(nAccount);
				//Main.user = Main.userBase.GetAccount(nAccount);
				bResult = true;
			}
			else //If it doesn't exist, reprompt for information
			{
				nAccount = -1;
				System.out.println("User does not exist!");
			}
		}
		while(!bResult);
		

	}
//	public Admin CreateNewAdmin(String sUser, String sPass)
//	{
//		return new Admin(s)
//	}
	
	
	//Access methods
	public String GetUsername()
	{
		return this.sUsername;
	}
	public void SetUsername(String s)
	{
		this.sUsername = s;
	}
	public String GetPassword()
	{
		return this.sPassword;
	}
	public void SetPassword(String s)
	{
		this.sPassword = s;
	}
	public int GetActNumber()
	{
		return nAccountNumber;
	}
	public void SetActNumber(int n)
	{
		nAccountNumber = n;
	}
	//	public Customer GetCustomer()
//	{
//		return Main.user;
//	}
//	public void SetCustomer(Customer c)
//	{
//		Main.user = c;
//	}
}
