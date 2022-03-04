package BankApp;

public class Main {
	
	//Should only be one database manager 
	static DatabaseManager databaseManager = null;
	
	//Create manager delete customer
	
	public static void main(String[] args) {
		
		//Load the database 
		FileManager fileManager = new FileManager("./src/BankApp.ser");
		fileManager.LoadDatabase();
		
		//Open the Login Screen finding out user
		LoginScreen login = new LoginScreen();
		
		
		//Start the App with the login screen
		//Determines the user of the App
		login.RunScreen();
		
		
		//Save out the database
		fileManager.SaveDatabase();
		
		
	}

}
