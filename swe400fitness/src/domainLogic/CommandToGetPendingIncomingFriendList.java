package domainLogic;

import Registry.FinderRegistry;

import mapper.IncomingFriendFinder;
import domainModel.IncomingRequestsList;

/**
 * Cause the list of friend requests from other user to this user to be fetched
 * from the domain model (may or may not cause reading from the DB depending on
 * the state of the domain model)
 * @author merlin
 */
public class CommandToGetPendingIncomingFriendList implements Command
{

	private long				userID;
	private IncomingRequestsList	incomingFriendsList;

	/**
	 * The userID of the current user
	 * @param userID unique
	 */
	public CommandToGetPendingIncomingFriendList(long userID)
	{
		this.userID = userID;
	}

	/**
	 * Find and set all incoming friend requests for a user.
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		IncomingFriendFinder finder = FinderRegistry.incomingFriendFinder();
		incomingFriendsList = finder.findIncomingRequests(userID);
	}

	/**
	 * A list of the friends associated with the given user
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public IncomingRequestsList getResult()
	{
		return incomingFriendsList;
	}

	/**
	 * Gets the id of the person whose pending incoming friend list we are examining
	 * @return userID
	 */
	public long getUserID()
	{
		return userID;
	}

}
