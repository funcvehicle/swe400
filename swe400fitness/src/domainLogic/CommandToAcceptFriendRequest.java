package domainLogic;

import unitOfWork.UnitOfWork;
import Registry.FinderRegistry;
import domainModel.Person;
import mapper.PersonFinder;


/**
 * Accept a friend request from one user to another
 * @author merlin
 *
 */
public class CommandToAcceptFriendRequest implements Command
{

	private long userIDOfRequestee;
	private String userNameOfRequester;


	/**
	 * Constructor of the Accept Friend Request
	 * @param userIDOfRequestee the User ID of the user accepting the request
	 * @param userNameOfRequester the User Name of the user who initiated the friend request
	 */
	public CommandToAcceptFriendRequest(long userIDOfRequestee, String userNameOfRequester)
	{
		this.userIDOfRequestee = userIDOfRequestee;
		this.userNameOfRequester = userNameOfRequester;
	}
	
	/**
	 * Executes the command
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		PersonFinder pfinder = FinderRegistry.personFinder();
		Person requestee = UnitOfWork.getCurrent().getCurrentUser();
		Person requester = pfinder.find(userNameOfRequester);
		
		requestee.acceptRequest(requester);
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

	/**
	 * Getter for the username of person sending request
	 * @return userNameOfRequester
	 */
	public String getUserNameOfRequester()
	{
		return userNameOfRequester;
	}

	/**
	 * Gets the id of the person recieving friend request
	 * @return userIDOfRequestee
	 */
	public long getUserIDOfRequestee()
	{
		return userIDOfRequestee;
	}
}
