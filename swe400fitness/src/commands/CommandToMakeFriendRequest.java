package commands;

import gateway.PersonGateway;
import mapper.MapperRegistry;
import mapper.PendingFriendMapper;
import mapper.PersonMapper;
import domainModel.Friend;
import domainModel.PendingRequest;
import domainModel.Person;

/**
 * Initiates a friend request from one user to another
 * @author merlin
 *
 */
public class CommandToMakeFriendRequest implements Command
{

	private int userIDOfRequester;
	private String userNameOfRequestee;


	/**
	 * 
	 * @param userIDOfRequester the User ID of the user making the request
	 * @param userNameOfRequestee the User Name of the user being friended
	 */
	public CommandToMakeFriendRequest(int userIDOfRequester, String userNameOfRequestee)
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
		MapperRegistry mapperRegistry = MapperRegistry.getCurrent();
		PersonMapper mapper = (PersonMapper) mapperRegistry.getMapper(Person.class);
		PendingFriendMapper pfm = (PendingFriendMapper) mapperRegistry.getMapper(PendingRequest.class);
		
		Person recipient = mapper.find(userNameOfRequestee);
		Person inquirer = mapper.find(userIDOfRequester);
		
		PendingRequest request = pfm.create(inquirer.getId(), recipient.getId(), recipient.getDisplayName());
		
		inquirer.requestFriend(request);
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
