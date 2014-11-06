package domainLogic;

import Registry.FinderRegistry;
import unitOfWork.UnitOfWork;
import domainModel.Person;


/**
 * Creates a new user in the system
 * @author merlin
 * @author Connor Fox
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
	
	/**
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		if (FinderRegistry.personFinder().find(userName) == null)
		{
			Person.createNewPerson(userName, displayName, password);
			UnitOfWork.getCurrent().commit();
		}
		else
		{
			System.err.println("Cannot create user " + userName + ": username unavailable!");
		}
	}

	/**
	 * This should return the appropriate Person object from the domain model.
	 * Null if the credentials of the user were invalid (userName not unique)
	 * @see Command#getResult()
	 */
	@Override
	public Person getResult()
	{
		return FinderRegistry.personFinder().find(userName);
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
