package BankApp;

public class Main {

	//Which user is currently using the app
	static Customer user = null;
	static Admin admin = null;
	static CustomerDatabase userBase = null;
	static AdminDatabase adminBase = null;
	
	
	//combine databases and save out to 1 file
	
	//Manager and employee difference implemented
	
	
	
	public static void main(String[] args) {
		
		//Load the database 
		FileManager fileManager = new FileManager("./src/CustBase.ser","./src/AdminBase.ser");
		
		//hold the database for comparison
		userBase = fileManager.LoadCustomers();
		
		adminBase = fileManager.LoadAdmin();
		
		
		//Open the Login Screen finding out user
		LoginScreen login = new LoginScreen();
		
		
		//Start the App with the login screen
		//Determines the user of the app
		
		login.RunScreen();
		
		
		//Save out the database
		fileManager.saveDatabase();
		
		
	}

}
