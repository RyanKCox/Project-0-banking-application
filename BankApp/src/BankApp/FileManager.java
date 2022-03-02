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
	String sFilePath;
	
	public FileManager(String sPath)
	{
		sFilePath = sPath;
	}

	public void LoadDatabase()
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
	public void SaveDatabase()
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
