package BankApp;

import java.util.ArrayList;

public class Customer implements AccountInfo, java.io.Serializable{
	
	private int nBalance = 0;
	private int nAccountNumber = 0;
	private ArrayList<String> alHistory = new ArrayList<String>();
	private String sUsername = null;
	private String sPassword = null;
	private boolean bType = false; //false for standard account, true for joint account
	private boolean bStatus = false; //false for closed, true for open
	
	//Empty Constructor 
	public Customer()
	{

		nBalance = 0;
		nAccountNumber = 0;
		alHistory = new ArrayList<String>();
		sUsername = null;
		sPassword = null;
		bType = false;
		bStatus = false;
	}	
	//Initialized Constructor
	public Customer(int nBal, int nAcct, String sUser, String sPass, boolean bT)
	{
		nBalance = nBal;
		nAccountNumber = nAcct;
		sUsername = sUser;
		sPassword = sPass;
		bType = bT;
		bStatus = false; //Account Status is false on creation, must be approved by manager
	}	
	//Returns false if account is closed and tells user
	public boolean CheckStatus()
	{
		if(!bStatus)
		{
			InputManager inputManager = new InputManager();
			System.out.println("Account is Closed!\nAsk an employee to activate");
			return false;	
		}
		else
			return true;
	}
	@Override
	public void Withdraw() {
	
		if(!CheckStatus())
		{
			return;
		}
		InputManager inputManager = new InputManager();
		int nAmount = -1;
		do
		{
			nAmount = inputManager.GetUserInputAsInt("How much would you like to Withdraw?");
			
			/*
			 * if(nAmount < 0) { System.out.println("Cannot use negative numbers"); } else
			 */
			if(this.OverdraftCheck(nAmount))
			{
				this.nBalance -=nAmount;			
				String sDisplay = "Customer Withdrawl: "+nAmount;
				System.out.println(sDisplay);
				this.AddHistory(sDisplay);
				
			}

		}
		while (nAmount<0);
		
		
	}
	@Override
	public void Deposit() 
	{
		if(!CheckStatus())
		{
			return;
		}
		InputManager inputManager = new InputManager();
		int nAmount = -1;
		do
		{
			nAmount = inputManager.GetUserInputAsInt("How much would you like to Deposit?");
	
			this.nBalance +=nAmount;
			
			String sDisplay = "Customer Deposit: "+nAmount;
			System.out.println(sDisplay);
			this.AddHistory(sDisplay);
			
			//this.AddHistory("Customer Deposit: "+nAmount);
//			if(nAmount > 0)
//			{
//				this.nBalance +=nAmount;
//				this.AddHistory("Customer Deposit: "+nAmount);
//			}
//			else
//			{
//				System.out.println("Cannot use negative numbers");
//			}
		}
		while (nAmount<0);
		
	}
	//Returns true if the account has enough funds
	public boolean OverdraftCheck(int n)
	{
		boolean bCheck = (this.nBalance - n >= 0)?true:false;
		if(!bCheck)
			System.out.println("Insuffecient Funds! Action canceled!");
		return bCheck;
	
	}

	
	
	//Displays all customer information
	public void DisplayCustomer()
	{
		System.out.println("Username: \t\t"+this.GetUsername());
		System.out.println("Password: \t\t"+this.GetPassword());
		System.out.println("Balance: \t\t"+this.GetBalance());
		System.out.println("Account type: \t\t"+this.GetTypeAsString());
		System.out.println("Account status: \t" +this.GetStatusAsString());
		System.out.println("Account number: \t"+this.GetActNumber());
	}
	public void DisplayBalance()
	{
		System.out.println("Account Balance: "+this.nBalance);
	}
	public void DisplayAccountNumber()
	{
		System.out.println("Account Number: "+this.nAccountNumber);
	}
	public void DisplayUserName()
	{
		System.out.println("Username: "+this.sUsername);
	}
	public void DisplayType()
	{
		String s;
		if (this.bType)
			s = "Joint Account";
		else
			s = "Standard Account";
		System.out.println("Account Type: "+s);
	}
	public void DisplayStatus()
	{
		String s;
		if (this.bStatus)
			s = "Account is Open";
		else
			s = "Account is Closed";
		System.out.println("Account Status: "+s);
	}
	public void DisplayHistory()
	{
		System.out.println("\n");
		System.out.println("|------User History------|\n");
		
		//check if history is empty
		if(this.alHistory.size() !=0)
		{
			//Loop through our history, displaying each item
			for(int i= 0; i<this.alHistory.size();i++)
			{
				System.out.println(this.alHistory.get(i)+"\n");
			}	
		}
		else
			System.out.println("No History Available");
	}
	
	//Get and set methods for private variables
	@Override
	public int GetBalance() {
		return nBalance;
	}
	@Override
	public void SetBalance(int i) {
		nBalance = i;
	}
	@Override
	public int GetActNumber() {
		return nAccountNumber;
	}
	@Override
	public void SetActNumber(int i) {
		nAccountNumber = i;
		
	}
	@Override
	public ArrayList<String> GetHistory() {
		return alHistory;
	}
	@Override
	public void AddHistory(String s) {
		alHistory.add(s);
		
	}
	@Override
	public String GetUsername() {
		return sUsername;
	}
	@Override
	public void SetUsername(String s) {
		sUsername = s;
		
	}
	@Override
	public String GetPassword() {
		return sPassword;
	}
	@Override
	public void SetPassword(String s) {
		sPassword = s;
		
	}
	@Override
	public boolean GetType() {
		return bType;
	}
	public String GetTypeAsString()
	{
		return bType ? "Joint Account" : "Solo Account";
	}
	@Override
	public void SetType(boolean b) {
		bType = b;
	}
	@Override
	public boolean GetStatus() {
		return bStatus;
	}
	public String GetStatusAsString()
	{
		return bStatus ? "Account Open" : "Account Closed";
	}
	@Override
	public void SetStatus(boolean b) {
		bStatus = b;
		
	}

}
