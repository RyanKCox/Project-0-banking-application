package BankApp;

public class Main {

	static DatabaseManager databaseManager = null;
	
	//UserInterface class - adding continue to RunScreen()
	
	public static void main(String[] args) {
		
		//Load the database 
		FileManager fileManager = new FileManager("./src/BankApp.ser");
		fileManager.LoadDatabase();
		
		//Open the Login Screen finding out user
		LoginScreen login = new LoginScreen();
		
		
		//Start the App with the login screen
		//Determines the user of the app
		login.RunScreen();
		
		
		//Save out the database
		fileManager.SaveDatabase();
		
		
	}

}
