package BankApp;

public class CustomerAccountScreen extends UserInterface{
	
	
	public CustomerAccountScreen()
	{
		this.Screen.put(0, "What would you like to do today?");
		this.Screen.put(1, "View Balance");
		this.Screen.put(2, "Withdraw");
		this.Screen.put(3, "Deposit");
		this.Screen.put(4, "Transfer");
		this.Screen.put(5, "View History");
		this.Screen.put(6, "View Account Number");
		this.Screen.put(7, "Exit");
	}

	@Override
	public void FollowInput(int nInput) {
		//boolean bExit = false;
		this.bExit = false;
		//while(!bExit)
		//{
			switch(nInput)
			{
	
			case 1: 
				//Main.user.GetBalance();
				Main.user.DisplayBalance();
				break;
			case 2: 
				Main.user.Withdraw();
				break;
			case 3: 
				Main.user.Deposit();
				break;
			case 4:
				Main.userBase.Transfer();
				break;
			case 5:
				Main.user.DisplayHistory();
				break;
			case 6:
				Main.user.DisplayAccountNumber();
				break;
			case 7://exit this screen
				System.out.println("Returning to Log-in screen");
				this.bExit = true;
				break;
			}
		//}
		
		
	}
}
