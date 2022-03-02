package BankApp;

public class Main {

	//Which user is currently using the app
	//static Customer user = null;
	//static Admin admin = null;
	//static CustomerDatabase userBase = null;
	//static AdminDatabase adminBase = null;
	
	static DatabaseManager databaseManager = null;
	
	//UserInterface class - adding continue to RunScreen()
	
	public static void main(String[] args) {

		String sFilePath = "./src/BankApp.ser";
		
		//Load the database 
		FileManager fileManager = new FileManager("./src/CustBase.ser","./src/AdminBase.ser");
		
		//hold the database for comparison
		//userBase = fileManager.LoadCustomers();
		
		//adminBase = fileManager.LoadAdmin();
		fileManager.LoadDatabase(sFilePath);
		
		//Open the Login Screen finding out user
		LoginScreen login = new LoginScreen();
		
		
		//Start the App with the login screen
		//Determines the user of the app
		
		login.RunScreen();
		
		
		//Save out the database
		//fileManager.saveDatabase();
		fileManager.SaveDatabase(sFilePath);
		
		
	}

}
