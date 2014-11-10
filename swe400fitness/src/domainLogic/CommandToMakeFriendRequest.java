package domainLogic;

import unitOfWork.UnitOfWork;
import Registry.FinderRegistry;
import mapper.PersonFinder;
import domainModel.Person;

/**
 * Initiates a friend request from one user to another
 * @author merlin
 *
 */
public class CommandToMakeFriendRequest implements Command
{

	private long userIDOfRequester;
	private String userNameOfRequestee;


	/**
	 * 
	 * @param userIDOfRequester the User ID of the user making the request
	 * @param userNameOfRequestee the User Name of the user being friended
	 */
	public CommandToMakeFriendRequest(long userIDOfRequester, String userNameOfRequestee)
	{
		this.userIDOfRequester = userIDOfRequester;
		this.userNameOfRequestee = userNameOfRequestee;
	}
	
	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{		
		PersonFinder pfinder = FinderRegistry.personFinder();
		Person recipient = pfinder.find(userNameOfRequestee);
		Person inquirer = UnitOfWork.getCurrent().getCurrentUser();//pfinder.find(userIDOfRequester);
		inquirer.requestFriend(recipient);
	}

	/**
	 * Nothing needs to be retrieved from this command
	 * @see Command#getResult()
	 */
	@Override
	public Object getResult()
	{
		return null;
	}

}
