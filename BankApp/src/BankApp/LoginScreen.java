package BankApp;


public class LoginScreen extends UserInterface{
	
	int nAdminAcct = 0;
	
	public LoginScreen()
	{
		this.Screen.put(0, "Welcome to your bank!");
		this.Screen.put(1, "Existing user? Log-in!");
		this.Screen.put(2, "New User? Register!");
		this.Screen.put(3, "Exit\n");
		this.Screen.put(4, "Admin Log-in");
	}

	@Override
	public void FollowInput(int nInput) {
		CustomerAccountScreen CustScreen = null;
		this.bExit = false;
//		while(!bExit)
//		{
			switch(nInput)
			{
			case 1: //Follow logic for an existing user
				CustScreen = new CustomerAccountScreen();
				int nUserAcct = 0;
//				do
//				{
				nUserAcct = Main.databaseManager.GetUserBase().Login();
				//nUserAcct = Main.userBase.Login();
				if(nUserAcct ==0)
				{
					inputManager.Continue();
					break;
				}
//				}
//				while(nUserAcct == 0);
				Main.databaseManager.SetFocusCustomer(nUserAcct);
				//Main.user = Main.userBase.GetAccount(nUserAcct);
				CustScreen.RunScreen();
				break;
				
			case 2: //Follow logic for a new user
				//nUserAcct = Main.userBase.Register();
				nUserAcct = Main.databaseManager.GetUserBase().Register();
				Main.databaseManager.SetFocusCustomer(nUserAcct);
				//Main.user = (Customer)Main.userBase.GetAccount(nUserAcct);
				CustScreen = new CustomerAccountScreen();
				CustScreen.RunScreen();
				break;
				
			case 3://Exit this screen
				System.out.println("Exiting App");
				bExit = true;
				break;
			case 4:
				//if no user sign in, find the default user
				if(Main.databaseManager.GetFocusCustomer() == null)
					Main.databaseManager.SetFocusCustomer(Main.databaseManager.GetUserBase().GetFirst().GetActNumber());
					//Main.user = Main.userBase.GetFirst();
				
				int nAdminAcct = 0;
//				do
//				{
				nAdminAcct = Main.databaseManager.GetAdminBase().Login();
				//nAdminAcct = Main.adminBase.Login();
				if(nAdminAcct ==0)
				{
					inputManager.Continue();
					break;
				}
//				}
//				while(nAdminAcct == 0);
				//Main.admin = Main.adminBase.GetAccount(nAdminAcct);
				AdminScreen nextScreen = new AdminScreen(nAdminAcct);
				nextScreen.RunScreen();
				break;
			}
				
//		}
		
		
	}

}
