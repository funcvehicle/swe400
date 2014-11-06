package domainLogic;

import Registry.FinderRegistry;
import domainModel.Friend;
import domainModel.IncomingRequestsList;
import domainModel.Person;
import mapper.PersonFinder;

/**
 * Reject a friend request from one user to another
 * 
 * @author merlin
 * 
 */
public class CommandToRejectFriendRequest implements Command
{
	private long	userIDOfRequestee;
	private String	userNameOfRequester;

	/**
	 * 
	 * @param userIDOfRequestee the User ID of the user accepting the request
	 * @param userNameOfRequester the User Name of the user who initiated the
	 *        friend request
	 */
	public CommandToRejectFriendRequest(long userIDOfRequestee, String userNameOfRequester)
	{
		this.userIDOfRequestee = userIDOfRequestee;
		this.userNameOfRequester = userNameOfRequester;

	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		PersonFinder pfinder = FinderRegistry.personFinder();

		Person requestee = pfinder.find(userIDOfRequestee);
		Person requester = pfinder.find(userNameOfRequester);
		IncomingRequestsList myList = requestee.getIncomingRequests();
		
		Friend request = myList.findId(requester.getId());
		if (request != null)
		{
			requestee.rejectRequest(request);
		}
		else
		{
			System.err.println("Request from " + userNameOfRequester + " to " + requestee.getUserName()
					+ " does not exist: Could not reject request!");
		}
	}

	/**
	 * Nothing needs to be retrieved from this command
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public Object getResult()
	{
		return null;
	}

}
