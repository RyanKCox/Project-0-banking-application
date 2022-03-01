package BankApp;

import java.util.HashMap;
import java.util.Map;


public abstract class UserInterface {

	Map<Integer,String> Screen = new HashMap<Integer, String>();
	InputManager inputManager = new InputManager();
	boolean bExit = false;
	
	public void RunScreen()
	{
		//this.DisplayScreen();
		while(!bExit)
		{
			this.DisplayScreen();
			//this.ActivateScreen();
			this.FollowInput(this.GetInput());			
			
		}
	
	}
	public void ExitScreen()
	{
		this.bExit = true;
	}
	
	public void DisplayScreen()
	{
		for(Map.Entry m:Screen.entrySet())
		{
			String sDisplay = "";
			
			if((Integer)m.getKey() !=0)
			{
				sDisplay += m.getKey()+". ";
			}
			
			sDisplay +=m.getValue();
			System.out.println(sDisplay);
		}
		
	}
	//public int ActivateScreen()
	public int GetInput()
	{
		//if input is last of size, exit
		//if input is not between 1-size retry for input
		int nInput = 0;
		while(!bExit)
		{
			nInput = inputManager.GetUserInputAsInt("Please choose a number");
			if(nInput < Screen.size()-1 && nInput > 0)
			{
				return nInput;
			}
			else if(nInput == Screen.size()-1)
			{
				bExit = true;		
			}
		}
		
		//should never be used?
		return nInput;
		
//		//this.DisplayScreen();
//		boolean bResult = false;
//		int nInput = 0;
//		while(bResult == false)
//		{
//			nInput = inputManager.GetUserInputAsInt("Please choose a number: ");
//			if(nInput < Screen.size() && nInput != 0)
//			{
//				return nInput;
//			}
//			else if(nInput == Screen.size())
//			{
//				bExit = true;
//			}
//		}
//		return nInput;
		
	}
	//
	public abstract void FollowInput(int nInput);

}
