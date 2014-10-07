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
public class CreateUserCommand implements Command
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
	public CreateUserCommand(String userName, String password, String displayName)
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
		UnitOfWork work = UnitOfWork.getCurrent();
		MapperRegistry mr = MapperRegistry.getCurrent();
		Person user = new Person(userName, displayName);
		PersonMapper pm = (PersonMapper) mr.getMapper(user.getClass());
		
		//Check that the user does not already exist in database 
		//Should we do this now or during commit?
		if (pm.find(userName) == null)
		{
			work.registerNew(user);
		}
		
		else
		{
			System.err.println("ERROR: Cannot create user because username already exists!");
		}
		
		//Commit after each command?
	}

	/**
	 * This should return the appropriate Person object from the domain model.
	 * Null if the credentials of the user were invalid (userName not unique)
	 * @see Command#getResult()
	 */
	@Override
	public Person getResult()
	{
		UnitOfWork work = UnitOfWork.getCurrent();
		return null;
	}
}
