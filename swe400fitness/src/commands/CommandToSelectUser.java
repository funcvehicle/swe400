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
		UnitOfWork work = UnitOfWork.getCurrent();
		MapperRegistry mr = MapperRegistry.getCurrent();
		PersonMapper pm = (PersonMapper) mr.getMapper(Person.class);
		Person user = pm.find(userName);
		
		//Check user exists
		if (user != null)
		{
			//Check password
			if (user.getPassword().equals(password))
			{
				//identity map.currentUser = user;
			}
			
			else
			{
				System.err.println("Invalid password.");
			}
		}
		
		else
		{
			System.err.println("Invalid username.");
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
		// TODO Auto-generated method stub
		return null;
	}

}
