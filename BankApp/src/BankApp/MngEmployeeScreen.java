package BankApp;

public class MngEmployeeScreen extends UserInterface {

	public MngEmployeeScreen()
	{
		this.Screen.put(0, "|------Manager Operations------|");
		this.Screen.put(1, "Promote employee to Manager");
		this.Screen.put(2, "Register new employee");
		this.Screen.put(3,"Exit");
		
	}
	
	@Override
	public void FollowInput(int nInput) {
		InputManager inputManager = new InputManager();
		switch(nInput)
		{
		case 1://Change employee to manager
			int nAccount = 0;
			Main.databaseManager.GetAdminBase().MakeManager();
			inputManager.Continue();
			break;
			
		case 2://add a new employee
			Main.databaseManager.GetAdminBase().Register();
			inputManager.Continue();
			break;
			
		case 3:
			bExit = true;
			break;
		
		}
	}

}
