package domainLogic;

import java.util.ArrayList;

import mapper.MapperRegistry;
import mapper.PendingFriendMapper;
import mapper.PersonMapper;
import domainModel.Friend;
import domainModel.IncomingRequestsList;
import domainModel.PendingRequest;
import domainModel.Person;

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

	private long userID;
	private IncomingRequestsList incomingFriendsList;

	/**
	 * The userID of the current user
	 * 
	 * @param userID
	 *            unique
	 */
	public CommandToGetPendingIncomingFriendList(long userID)
	{
		this.userID = userID;
	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		MapperRegistry mapperRegistry = MapperRegistry.getCurrent();
		PersonMapper mapper = (PersonMapper) mapperRegistry.getMapper(Person.class);
		PendingFriendMapper pfMapper = (PendingFriendMapper) mapperRegistry.getMapper(PendingRequest.class);
		
		incomingFriendsList = pfMapper.findIncomingRequests(userID);
//		ArrayList<PendingRequest> pendingRequests = incomingRequestsList.getIncomingRequestsList();
//		incomingFriendsList = new ArrayList<Friend>();
//		
//		for (PendingRequest pr : pendingRequests)
//		{
//			Friend friend = new Friend(pr.getDisplayName(), pr.getInquirerId());
//			incomingFriendsList.add(friend);
//		}
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

	public long getUserID()
	{
		return userID;
	}

}
