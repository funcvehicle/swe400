package commands;

import domainModel.PendingRequest;
import domainModel.Person;
import mapper.MapperRegistry;
import mapper.PendingFriendMapper;
import mapper.PersonMapper;

/**
 * Reject a friend request from one user to another
 * @author merlin
 *
 */
public class CommandToRejectFriendRequest implements Command
{

	private int userIDOfRequestee;
	private String userNameOfRequester;

	/**
	 * 
	 * @param userIDOfRequestee the User ID of the user accepting the request
	 * @param userNameOfRequester the User Name of the user who initiated the friend request
	 */
	public CommandToRejectFriendRequest(int userIDOfRequestee, String userNameOfRequester)
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
		PersonMapper mapper = (PersonMapper) mapperRegistry.getMapper(Person.class);
		PendingFriendMapper pfMapper = (PendingFriendMapper) mapperRegistry.getMapper(PendingRequest.class);
		Person requestee = mapper.find(userIDOfRequestee);
		Person requester = mapper.find(userNameOfRequester);
		PendingRequest pendingRequest = pfMapper.create(requestee.getId(), requester.getId(), requester.getDisplayName());
		requestee.rejectRequest(pendingRequest);
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
