package commands;

import java.util.ArrayList;

import mapper.MapperRegistry;
import mapper.PendingFriendMapper;
import mapper.PersonMapper;
import domainModel.Friend;
import domainModel.OutgoingRequestsList;
import domainModel.PendingRequest;
import domainModel.Person;

/**
 * Cause the list of pending friend requests from this user to other users to be fetched
 * from the domain model (may or may not cause reading from the DB depending on
 * the state of the domain model)
 * 
 * @author merlin
 *
 */
public class CommandToGetPendingOutgoingFriendList implements Command
{

	private long userID;
	private ArrayList<Friend> outgoingFriendsList;

	/**
	 * The userID of the current user
	 * 
	 * @param userID
	 *            unique
	 */
	public CommandToGetPendingOutgoingFriendList(int userID)
	{
		this.userID = userID;
		outgoingFriendsList = new ArrayList<Friend>();
	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		MapperRegistry mapperRegistry = MapperRegistry.getCurrent();
		PendingFriendMapper pfMapper = (PendingFriendMapper) mapperRegistry.getMapper(PendingRequest.class);
		OutgoingRequestsList outgoingRequestsList = pfMapper.findOutgoingRequests(userID);
		ArrayList<PendingRequest> pendingFriends = outgoingRequestsList.getOutgoingRequestsList();
		for (PendingRequest pf : pendingFriends)
		{
			Friend friend = new Friend(pf.getDisplayName(), pf.getRecipientId());
			outgoingFriendsList.add(friend);
		}
	}

	/**
	 * A list of the friends associated with the given user
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public ArrayList<Friend> getResult()
	{
		return outgoingFriendsList;
	}

}
