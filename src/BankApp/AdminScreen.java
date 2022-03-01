package BankApp;

public class AdminScreen extends UserInterface{
	public AdminScreen()
	{
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
		sDisplay = "Admin Console\nCurrent Account: "+Main.user.GetActNumber()+"\nStatus of Account: "+ Main.user.GetStatusAsString();
		this.Screen.put(0, sDisplay);
		//(String)this.Screen.get(0) = sDisplay;
		//(String)this.Screen.get(0) = "Admin Console\nCurrent Account: "+Main.user.GetActNumber()+"\nStatus of Account: "+ Main.user.GetStatusAsString();
	}

	@Override
	public void FollowInput(int nInput) {
		this.bExit = false;
//		
//		while(!bExit)
//		{
			switch(nInput)
			{
				case 1: //Display selected customer
					Main.user.DisplayCustomer();
					break;
				case 2: //toggle account status
					Main.admin.ToggleStatus();
					UpdateCustomer();
					break;
				case 3: //withdraw from customer
					Main.user.Withdraw();
					break;
				case 4: //deposit from customer
					Main.user.Deposit();
					break;
				case 5: //transfer from current customer to different account
					Main.userBase.Transfer();
					break;
				case 6: //display customer history
					Main.user.DisplayHistory();
					break;
				case 7: //change selected user
					Main.userBase.ChangeFocusCustomer();
					UpdateCustomer();
					break;
				case 8://Exit this screen
					System.out.println("Exiting App");
					bExit = true;
					break;
				}
//			}
	}
}
