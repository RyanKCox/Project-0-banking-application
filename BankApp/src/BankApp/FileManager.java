package BankApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class FileManager {
	
	FileOutputStream fileOut;
	FileInputStream fileIn;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	String sCustFile;
	String sAdminFile;
	
	public FileManager(String sCust, String sAdmin)
	{
		sCustFile = sCust;
		sAdminFile = sAdmin;
	}
//	
//	public void saveDatabase()
//	{
//		try
//		{
//			//Customers
//			fileOut = new FileOutputStream(sCustFile);
//			outStream = new ObjectOutputStream(fileOut);
//			outStream.writeObject(Main.userBase);
//			fileOut.close();
//			outStream.close();
//			
//			//Admin
//			fileOut = new FileOutputStream(sAdminFile);
//			outStream = new ObjectOutputStream(fileOut);
//			outStream.writeObject(Main.adminBase);
//			fileOut.close();
//			outStream.close();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//	}
	public CustomerDatabase LoadCustomers()
	{
		CustomerDatabase userBase = null;
		File fileCheck = new File(sCustFile);
		if(fileCheck.length() == 0)
		{
			userBase= new CustomerDatabase();
		}
		else
		{
			try 
			{
				fileIn = new FileInputStream(sCustFile);
				inStream = new ObjectInputStream(fileIn);
				userBase = (CustomerDatabase)inStream.readObject();
				fileIn.close();
				inStream.close();
					
			} 
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		//Set focus customer
		//Main.user = userBase.GetFirst();
		return userBase;
	}
	public AdminDatabase LoadAdmin()
	{
		AdminDatabase adminBase = null;
		File fileCheck = new File(sAdminFile);
		if(fileCheck.length() == 0)
		{
			adminBase= new AdminDatabase();
		}
		else
		{
			try 
			{
				fileIn = new FileInputStream(sAdminFile);
				inStream = new ObjectInputStream(fileIn);
				adminBase = (AdminDatabase)inStream.readObject();
				fileIn.close();
				inStream.close();
					
			} 
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		return adminBase;
	}

	public void LoadDatabase(String sFilePath)
	{
		
			DatabaseManager databaseManager = null;
			File fileCheck = new File(sFilePath);
			if(fileCheck.length() == 0)
			{
				databaseManager = new DatabaseManager();
			}
			else
			{
				try 
				{
					fileIn = new FileInputStream(sFilePath);
					inStream = new ObjectInputStream(fileIn);
					databaseManager = (DatabaseManager)inStream.readObject();
					fileIn.close();
					inStream.close();
						
				} 
				catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
			
			Main.databaseManager = databaseManager;
		
				
	}
	public void SaveDatabase(String sFilePath)
	{
		try
		{
			//Attempt to save database out
			fileOut = new FileOutputStream(sFilePath);
			outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(Main.databaseManager);
			fileOut.close();
			outStream.close();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
