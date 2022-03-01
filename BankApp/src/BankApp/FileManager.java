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
	
	public void saveDatabase()
	{
		try
		{
			//Customers
			fileOut = new FileOutputStream(sCustFile);
			outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(Main.userBase);
			fileOut.close();
			outStream.close();
			
			//Admin
			fileOut = new FileOutputStream(sAdminFile);
			outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(Main.adminBase);
			fileOut.close();
			outStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
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

	
//	try
//	{
//		FileOutputStream fileOut = new FileOutputStream("./src/serialization.ser");
//		ObjectOutputStream out = new ObjectOutputStream(fileOut);
//		out.writeObject(e);
//		out.close();
//		fileOut.close();
//	
//	}
//	catch(IOException ex)
//	{
//		ex.printStackTrace();
//	}
}
