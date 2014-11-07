package domainLogic;

import Registry.FinderRegistry;
import domainModel.Person;
import mapper.PersonFinder;

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
	 * Executes the modify user command
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		PersonFinder pf = FinderRegistry.personFinder();
		Person p = pf.find(userID);
		
		if (p != null)
			p.setDisplayName(newDisplayName);
		else
			System.err.println("Command to modify user " + userID + " failed because the userID does not exist!");	
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
