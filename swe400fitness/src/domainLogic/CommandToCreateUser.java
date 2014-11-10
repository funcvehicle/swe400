package domainLogic;

import Registry.FinderRegistry;
import unitOfWork.UnitOfWork;
import domainModel.NullPerson;
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
	private boolean alreadyExists;
	
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
		this.alreadyExists = false;
	}
	
	/**
	 * Execute the create user command
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
			alreadyExists = true;
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
		if (alreadyExists)
			return new NullPerson();
		return FinderRegistry.personFinder().find(userName);
	}

	/**
	 * Gets the username of the new user
	 * @return userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Gets the password of the new user
	 * @return password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Gets the display name of the new user
	 * @return displayName
	 */
	public String getDisplayName()
	{
		return displayName;
	}
}
