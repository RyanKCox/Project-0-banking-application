package BankApp;

public class AdminScreen extends UserInterface{
	int nAdmin;
	
	
	public AdminScreen(int nAd)
	{
		nAdmin = nAd;
		UpdateCustomer();
		UpdateScreen();
	}
	public void UpdateCustomer()
	{
		String sDisplay = "";
		
		
		if(Main.databaseManager.userBase.nFocusCustomer == 0)
		{
			sDisplay = "No customer selected, please select a customer";
		}
		else
		{	
			Customer user = Main.databaseManager.GetFocusCustomer();
			sDisplay = "Admin Console\nCurrent Account: "+user.GetActNumber()+
					"\nUsername: "+user.GetUsername()+
					"\nStatus of Account: "+ user.GetStatusAsString();
		}

		this.Screen.put(0, sDisplay);
	}

	@Override
	public void FollowInput(int nInput) {
		
		if(Main.databaseManager.GetAdminBase().GetAccount(nAdmin).GetManagerStatus())
		{
			ManagerScreen(nInput);
		}
		else
		{
			EmployeeScreen(nInput);
		}
	}

	public void UpdateScreen()
	{
		if(Main.databaseManager.GetAdminBase().GetAccount(nAdmin).GetManagerStatus())
		{
			UpdateCustomer();
			this.Screen.put(1, "Display Account");
			this.Screen.put(2, "Activate/Deactivate Account");
			this.Screen.put(3, "Withdraw");
			this.Screen.put(4, "Deposit");
			this.Screen.put(5, "Transfer");
			this.Screen.put(6, "View History\n");
			this.Screen.put(7, "Focus Account\n");
			this.Screen.put(8, "Manage Employees");
			this.Screen.put(9, "Exit");
		}
		else
		{
			UpdateCustomer();
			this.Screen.put(1, "Display Account");
			this.Screen.put(2, "Activate/Deactivate Account");
			this.Screen.put(3, "View History\n");
			this.Screen.put(4, "Focus Account\n");
			this.Screen.put(5, "Exit");
		}
		
	}

	public void ManagerScreen(int nInput)
	{
		this.bExit = false;
		boolean bIsFocusCustomer = Main.databaseManager.isFocusCustomer();
		
		Customer user = Main.databaseManager.GetFocusCustomer();
		Admin admin = Main.databaseManager.GetAdminBase().GetAccount(nAdmin);
		
		InputManager inputManager = new InputManager();
	
		switch(nInput)
		{
			case 1: //Display selected customer
				if(bIsFocusCustomer)
					Main.databaseManager.GetFocusCustomer().DisplayCustomer();					
				else
					System.out.println("No customer selected!");
				
				inputManager.Continue();
				break;
				
			case 2: //toggle account status
				if(bIsFocusCustomer)
				{
					admin.ToggleStatus();
					UpdateCustomer();	
				}
				else
					System.out.println("No customer selected!");
				
				inputManager.Continue();
				break;
				
			case 3: //withdraw from customer
				if(bIsFocusCustomer)
				{
					user.Withdraw();	
				}
				else
					System.out.println("No customer selected!");
				
				
				inputManager.Continue();		
				break;
				
			case 4: //deposit from customer
				if(bIsFocusCustomer)
				{
					user.Deposit();	
				}
				else
					System.out.println("No customer selected!");

				inputManager.Continue();
				break;
				
			case 5: //transfer from current customer to different account
				if(bIsFocusCustomer)
				{
					Main.databaseManager.GetUserBase().Transfer();
				}
				else
					System.out.println("No customer selected!");
			
				inputManager.Continue();
				break;
				
			case 6: //display customer history
				if(bIsFocusCustomer)
				{
					user.DisplayHistory();
				}
				else
					System.out.println("No customer selected!");
				
				inputManager.Continue();
				break;
			case 7: //change selected user
				Main.databaseManager.GetUserBase().ChangeFocusCustomer();
				UpdateCustomer();
				System.out.println("Focused Customer Account: "+Main.databaseManager.GetFocusCustomer().GetActNumber()+
						"\nUsername: "+Main.databaseManager.GetFocusCustomer().GetUsername());
				inputManager.Continue();
				break;
				
			case 8:
				//ToDo manage employee screen
				MngEmployeeScreen mngScreen = new MngEmployeeScreen();
				mngScreen.RunScreen();
				break;
				
			case 9://Exit this screen
				System.out.println("Exiting App");
				bExit = true;
				Main.databaseManager.SetFocusCustomer(0);
				break;
		}
		
	}
	public void EmployeeScreen(int nInput)
	{
		this.bExit = false;
		boolean bIsFocusCustomer = Main.databaseManager.isFocusCustomer();
		Customer user = Main.databaseManager.GetFocusCustomer();
		Admin admin = Main.databaseManager.GetAdminBase().GetAccount(nAdmin);
		InputManager inputManager = new InputManager();
	
			switch(nInput)
			{
				case 1: //Display selected customer
					if(bIsFocusCustomer)
						Main.databaseManager.GetFocusCustomer().DisplayCustomer();					
					else
						System.out.println("No customer selected!");
					
					inputManager.Continue();
					break;
					

				case 2: //toggle account status
					if(bIsFocusCustomer)
					{
						admin.ToggleStatus();
						UpdateCustomer();	
					}
					else
						System.out.println("No customer selected!");
					
					inputManager.Continue();
					break;

				case 3: //display customer history
					if(bIsFocusCustomer)
					{
						user.DisplayHistory();
					}
					else
						System.out.println("No customer selected!");
					
					inputManager.Continue();
					break;
					
				case 4: //change selected user
					Main.databaseManager.GetUserBase().ChangeFocusCustomer();
					UpdateCustomer();
					System.out.println("Focused Customer Account: "+Main.databaseManager.GetFocusCustomer().GetActNumber()+
										"\nUsername: "+Main.databaseManager.GetFocusCustomer().GetUsername());
					inputManager.Continue();
					break;
					
				case 5://Exit this screen
					bExit = true;
					Main.databaseManager.SetFocusCustomer(0);
					break;
				}
		
	}


}
