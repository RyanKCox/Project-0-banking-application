package BankApp;

public class AdminScreen extends UserInterface{
	int nAdmin;
	public AdminScreen(int nAd)
	{
		nAdmin = nAd;
		//this.Screen.put(0, "Admin Console\nCurrent Account: "+Main.user.GetActNumber()+"\nStatus of Account: "+ Main.user.GetStatusAsString());
		UpdateCustomer();
		this.Screen.put(1, "Display Account");
		this.Screen.put(2, "Activate/Deactivate Account");
		this.Screen.put(3, "Withdraw");
		this.Screen.put(4, "Deposit");
		this.Screen.put(5, "Transfer");
		this.Screen.put(6, "View History\n");
		this.Screen.put(7, "Focus Account\n");
		this.Screen.put(8, "Exit");
	}
	public void UpdateCustomer()
	{
		String sDisplay = "";
		Customer user = Main.databaseManager.GetFocusCustomer();
		sDisplay = "Admin Console\nCurrent Account: "+user.GetActNumber()+
				"\nUsername: "+user.GetUsername()+
				"\nStatus of Account: "+ user.GetStatusAsString();
		//sDisplay = "Admin Console\nCurrent Account: "+Main.user.GetActNumber()+"\nUsername: "+Main.user.GetUsername()+"\nStatus of Account: "+ Main.user.GetStatusAsString();
		this.Screen.put(0, sDisplay);
		//(String)this.Screen.get(0) = sDisplay;
		//(String)this.Screen.get(0) = "Admin Console\nCurrent Account: "+Main.user.GetActNumber()+"\nStatus of Account: "+ Main.user.GetStatusAsString();
	}

	@Override
	public void FollowInput(int nInput) {
		this.bExit = false;
		Customer user = Main.databaseManager.GetFocusCustomer();
		Admin admin = Main.databaseManager.GetAdminBase().GetAccount(nAdmin);
		InputManager inputManager = new InputManager();
//		
//		while(!bExit)
//		{
			switch(nInput)
			{
				case 1: //Display selected customer
					Main.databaseManager.GetFocusCustomer().DisplayCustomer();
					inputManager.Continue();
					//user.DisplayCustomer();
					//Main.user.DisplayCustomer();
					break;
				case 2: //toggle account status
					
					admin.ToggleStatus();
					UpdateCustomer();
					inputManager.Continue();
					break;
				case 3: //withdraw from customer
					if(admin.isManager())
						user.Withdraw();
					inputManager.Continue();
						//Main.user.Withdraw();
					break;
				case 4: //deposit from customer
					if(admin.isManager())
						user.Deposit();
					inputManager.Continue();
						//Main.user.Deposit();
					break;
				case 5: //transfer from current customer to different account
					if(admin.isManager())
						Main.databaseManager.GetUserBase().Transfer();
					inputManager.Continue();
						//Main.userBase.Transfer();
					break;
				case 6: //display customer history
					user.DisplayHistory();
					inputManager.Continue();
					//Main.user.DisplayHistory();
					break;
				case 7: //change selected user
					Main.databaseManager.GetUserBase().ChangeFocusCustomer();
					//Main.userBase.ChangeFocusCustomer();
					UpdateCustomer();
					inputManager.Continue();
					break;
				case 8://Exit this screen
					System.out.println("Exiting App");
					bExit = true;
					break;
				}
//			}
	}
}
