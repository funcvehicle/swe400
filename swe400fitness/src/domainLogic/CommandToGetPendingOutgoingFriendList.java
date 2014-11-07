package domainLogic;

import Registry.FinderRegistry;
import mapper.OutgoingFriendFinder;
import domainModel.OutgoingRequestsList;

/**
 * Cause the list of pending friend requests from this user to other users to be
 * fetched from the domain model (may or may not cause reading from the DB
 * depending on the state of the domain model)
 * 
 * @author merlin
 * 
 */
public class CommandToGetPendingOutgoingFriendList implements Command
{

	private long userID;
	private OutgoingRequestsList outgoingFriendsList;

	/**
	 * The userID of the current user
	 * 
	 * @param userID unique
	 */
	public CommandToGetPendingOutgoingFriendList(long userID)
	{
		this.userID = userID;
	}

	/**
	 * Find all outgoing friend requests from a given user
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		OutgoingFriendFinder finder = FinderRegistry.outgoingFriendFinder();
		outgoingFriendsList = finder.findOutgoingRequests(userID);
	}

	/**
	 * A list of the friends associated with the given user
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public OutgoingRequestsList getResult()
	{
		return outgoingFriendsList;
	}

}
