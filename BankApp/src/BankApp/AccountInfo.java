package BankApp;

import java.util.ArrayList;

public interface AccountInfo {
	
	//All Accounts need to contain
	// Balance, Account Number, History, Username, Password,
	//Account Type(single or joint), transfer method, 
	//and if the account is open
	
	//Balance
	public int GetBalance();
	public void SetBalance(int i);
	
	
	//Account Number
	public int GetActNumber();
	public void SetActNumber(int i);
	
	//History
	//Returns an arraylist of the accounts History
	public ArrayList<String> GetHistory();
	//Adds provided String to account's history
	public void AddHistory(String s);
	
	//Username
	public String GetUsername();
	public void SetUsername(String s);
	
	//Password
	public String GetPassword();
	public void SetPassword(String s);
	
	//Type
	public boolean GetType();
	public void SetType(boolean b);
	
	//Status
	public boolean GetStatus();
	public void SetStatus(boolean b);
		
	public void Withdraw();
	public void Deposit();

}
