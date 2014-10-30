package commands;

import domainModel.Friend;
import domainModel.PendingRequest;
import domainModel.Person;
import mapper.FriendMapper;
import mapper.MapperRegistry;
import mapper.PendingFriendMapper;
import mapper.PersonMapper;

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
	 * 
	 * @param userIDOfRequestee the User ID of the user accepting the request
	 * @param userNameOfRequester the User Name of the user who initiated the friend request
	 */
	public CommandToAcceptFriendRequest(int userIDOfRequestee, String userNameOfRequester)
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
		MapperRegistry mapperRegistry = MapperRegistry.getCurrent();
		PersonMapper pMapper = (PersonMapper) mapperRegistry.getMapper(Person.class);
		PendingFriendMapper pfMapper = (PendingFriendMapper) mapperRegistry.getMapper(PendingRequest.class);
		FriendMapper fm = (FriendMapper) mapperRegistry.getMapper(Friend.class);
		
		Person requestee = pMapper.find(userIDOfRequestee);
		requestee.setIncomingRequests(pfMapper.findIncomingRequests(userIDOfRequestee));
		Person requester = pMapper.find(userNameOfRequester);
		
		fm.create(requester.getDisplayName(), requester.getId(), userIDOfRequestee);
		
		PendingRequest pendingRequest = pfMapper.create(requester.getId(), requestee.getId(), requester.getDisplayName());
		requestee.acceptRequest(pendingRequest);
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

	public String getUserNameOfRequester()
	{
		return userNameOfRequester;
	}

	public long getUserIDOfRequestee()
	{
		return userIDOfRequestee;
	}

}
