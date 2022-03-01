package BankApp;

public interface DatabaseInterface<T> {

	public int Register();
	public void AddAccount(T account);
	public int AssignAccountNumber();
	public boolean CheckPassword(int nAccountNumber, String sPass);
	public T GetAccount(int nAccountNumber);
	public int FindAccount(String sName);
	public int Login();
	public boolean NameCheck(String sName);
}
