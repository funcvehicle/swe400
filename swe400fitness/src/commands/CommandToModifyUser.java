package commands;

import domainModel.Person;
import mapper.MapperRegistry;
import mapper.PersonMapper;
import unitOfWork.UnitOfWork;

/**
 * Used to change information associated with a person (at this point, only the display name)
 * @author merlin
 *
 */
public class CommandToModifyUser implements Command
{

	private long userID;
	private String newDisplayName;

	/**
	 * 
	 * @param userID the unique ID of this user
	 * @param newDisplayName the name this user wants to be known by
	 */
	public CommandToModifyUser(long userID, String newDisplayName)
	{
		this.userID = userID;
		this.newDisplayName = newDisplayName;
	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		MapperRegistry mr = MapperRegistry.getCurrent();
		PersonMapper pm = (PersonMapper) mr.getMapper(Person.class);
		
		Person p = pm.find(userID);
		
		if (p != null)
		{
			p.setDisplayName(newDisplayName);
		}
		else
		{
			System.err.println("ERROR: Cannot modify user " + userID + " because the ID does not exist!");
			System.err.println("Command to modify user " + userID + " failed!");
		}	
	}

	/**
	 * Nothing needs to be returned from this one. These tests will persist and
	 * re-retrieve the user to be sure that it is updated correctly
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public Object getResult()
	{
		return null;
	}
}
