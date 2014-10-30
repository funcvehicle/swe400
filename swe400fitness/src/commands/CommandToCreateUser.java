package commands;

import mapper.MapperRegistry;
import mapper.PersonMapper;
import unitOfWork.UnitOfWork;
import domainModel.Person;


/**
 * Creates a new user in the system
 * @author merlin
 *
 */
public class CommandToCreateUser implements Command
{
	private String userName;
	private String password;
	private String displayName;
	
	/**
	 * Create a command that will add a new user to the system
	 * @param userName the name of the user's login credentials
	 * @param password that password of the user's login credentials
	 * @param displayName the name by which the user wants to be referred
	 */
	public CommandToCreateUser(String userName, String password, String displayName)
	{
		this.userName = userName;
		this.password = password;
		this.displayName = displayName;
	}
	
	
//	/**
//	 * @see Command#execute()
//	 */
//	@Override
//	public void execute()
//	{
//		MapperRegistry mr = MapperRegistry.getCurrent();
//		PersonMapper pm = (PersonMapper) mr.getMapper(Person.class);
//		
//		//Check that the user does not already exist in database 
//		if (pm.find(userName) == null)
//		{
//			pm.create(userName, password, displayName);
//			UnitOfWork.getCurrent().commit();
//		}
//		
//		else
//		{
//			System.err.println("ERROR: Cannot create user " + userName + " because username already exists in DB!");
//			System.err.println("CommandToCreateUser " + userName + " failed!");
//		}
//	}
	
	/**
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		Person.createNewPerson(userName, displayName, password);
		UnitOfWork.getCurrent().commit();
	}

	/**
	 * This should return the appropriate Person object from the domain model.
	 * Null if the credentials of the user were invalid (userName not unique)
	 * @see Command#getResult()
	 */
	@Override
	public Person getResult()
	{
		MapperRegistry mr = MapperRegistry.getCurrent();
		PersonMapper pm = (PersonMapper) mr.getMapper(Person.class);
		
		return pm.find(userName);
	}


	public String getUserName()
	{
		return userName;
	}


	public String getPassword()
	{
		return password;
	}


	public String getDisplayName()
	{
		return displayName;
	}
}
