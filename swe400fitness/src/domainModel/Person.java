package domainModel;

public class Person extends DomainObject
{
	long id;
	String userName;
	String displayName;
	
	public Person(String userName, String displayName)
	{
		this.userName = userName;
		this.displayName = displayName;
	}

	public String getUserName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getDisplayName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public long getId()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
