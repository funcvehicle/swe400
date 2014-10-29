package commands;

import mapper.MapperRegistry;
import mapper.PersonMapper;
import unitOfWork.UnitOfWork;
import domainModel.Person;

/**
 * Retrieve a specified user from the database into the domain model
 */
public class CommandToSelectUser implements Command
{
	private String userName;
	private String password;
	private Person person;

	/**
	 * @param userName
	 *            the username from the user's credentials
	 * @param password
	 *            the password from the user's credentials
	 */
	public CommandToSelectUser(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Create the domain model object for the specified user (retrieve that user
	 * from the database)
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		MapperRegistry mr = MapperRegistry.getCurrent();
		PersonMapper pm = (PersonMapper) mr.getMapper(Person.class);
		person = pm.find(userName);
		if (!person.getPassword().equals(password))
		{
			person = null;
			System.err.println("Username and password do not match.");
		}
	}

	/**
	 * This should return the appropriate Person object from the domain model.
	 * Null if the credentials of the user were invalid
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public Person getResult()
	{
		return person;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}

}
