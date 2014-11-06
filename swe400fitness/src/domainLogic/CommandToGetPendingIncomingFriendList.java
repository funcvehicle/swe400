package domainLogic;

import java.util.ArrayList;

import Registry.FinderRegistry;

import mapper.IncomingFriendFinder;
import domainModel.Friend;
import domainModel.IncomingRequestsList;

/**
 * Cause the list of friend requests from other user to this user to be fetched
 * from the domain model (may or may not cause reading from the DB depending on
 * the state of the domain model)
 * 
 * @author merlin
 * 
 */
public class CommandToGetPendingIncomingFriendList implements Command
{

	private long				userID;
	private ArrayList<Friend>	incomingFriendsList;

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
		IncomingRequestsList incomingRequestsList = finder.findIncomingRequests(userID);
		incomingFriendsList = incomingRequestsList.getIncomingRequestsList();
	}

	/**
	 * A list of the friends associated with the given user
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public ArrayList<Friend> getResult()
	{
		return incomingFriendsList;
	}

	public long getUserID()
	{
		return userID;
	}

}
