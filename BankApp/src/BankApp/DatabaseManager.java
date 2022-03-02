package BankApp;

public class DatabaseManager implements java.io.Serializable{

	CustomerDatabase userBase;
	AdminDatabase adminBase;
	
	public DatabaseManager()
	{
		userBase = new CustomerDatabase();
		adminBase = new AdminDatabase();
	}
	public void SetDatabase(CustomerDatabase user, AdminDatabase admin)
	{
		userBase = user;
		adminBase = admin;
	}

	public Customer GetFocusCustomer()
	{
		return userBase.GetFocusCustomer();
	}
	public void SetFocusCustomer(int nCust)
	{
		userBase.nFocusCustomer = nCust;
	}
	
	public CustomerDatabase GetUserBase()
	{
		return userBase;
	}
	public AdminDatabase GetAdminBase()
	{
		return adminBase;
	}
	public void SetUserBase(CustomerDatabase cust)
	{
		userBase = cust;
	}
	public void SetAdminBase(AdminDatabase admin)
	{
		adminBase = admin;
	}
	
	public boolean isFocusCustomer()
	{
		if(userBase.nFocusCustomer!=0)
			return true;
		else
			return false;
	}
	
}
