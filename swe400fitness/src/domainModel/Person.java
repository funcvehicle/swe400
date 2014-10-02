package domainModel;

public class Person extends DomainObject
{
	long id;
	String userName;
	String displayName;
	String password;
	
	public Person(String userName, String displayName, String password)
	{
		this.userName = userName;
		this.displayName = displayName;
		this.password = password;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public long getId()
	{
		return id;
	}
	
	public String getPassword()
	{
		return password;
	}
}
