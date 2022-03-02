package BankApp;

import java.util.HashMap;
import java.util.Map;

public abstract class Database<T> implements DatabaseInterface<T>, java.io.Serializable{

	Map<Integer,T> dataBase = new HashMap<Integer,T>();

	public int AssignAccountNumber()
	{
		int nKey = 0;
		for(int i = 1; nKey == 0; i++)
		{
			if(!dataBase.containsKey(i))
				nKey=i;
		}
		return nKey;
	}
	public T GetAccount(int nAccountNumber)
	{
		return dataBase.get(nAccountNumber);
	}
	public int Login()
	{
		InputManager inputManager = new InputManager();
		int nAccount = 0;
		nAccount = FindAccount(inputManager.GetUserInputAsString("Enter Username: "));
		if(nAccount == 0)
		{
			System.out.println("Account not found!");
		}
		else
		{
			if(!CheckPassword(nAccount,inputManager.GetUserInputAsString("Enter Password: ")))
			{
				System.out.println("Password Incorrect!");
			}
			else
				return nAccount;
		}
		return nAccount;
		
	}

	

	public abstract int Register();
	public abstract void AddAccount(T account);
	public abstract boolean CheckPassword(int nAccountNumber, String sPass);
	public abstract int FindAccount(String sName);
}
