package commands;

import domainModel.Person;
import mapper.MapperRegistry;
import unitOfWork.UnitOfWork;

/**
 * Used to change information associated with a person (at this point, only the display name)
 * @author merlin
 *
 */
public class ModifyUserCommand implements Command
{

	private int userID;
	private String newDisplayName;

	/**
	 * 
	 * @param userID the unique ID of this user
	 * @param newDisplayName the name this user wants to be known by
	 */
	public ModifyUserCommand(int userID, String newDisplayName)
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
		UnitOfWork work = UnitOfWork.getCurrent();
		// load person from DB?
		// update in memory object, then register with unit of work?
		
		//work.registerDirty();
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
		// TODO Auto-generated method stub
		return null;
	}
}
