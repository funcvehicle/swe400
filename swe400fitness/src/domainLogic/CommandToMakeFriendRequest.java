package domainLogic;

import gateway.PersonGateway;
import mapper.FinderRegistry;
import mapper.MapperRegistry;
import mapper.PendingFriendMapper;
import mapper.PersonFinder;
import mapper.PersonMapper;
import domainModel.Friend;
import domainModel.OutgoingRequestsList;
import domainModel.PendingRequest;
import domainModel.Person;

/**
 * Initiates a friend request from one user to another
 * @author merlin
 *
 */
public class CommandToMakeFriendRequest implements Command
{

	private long userIDOfRequester;
	private String userNameOfRequestee;


	/**
	 * 
	 * @param userIDOfRequester the User ID of the user making the request
	 * @param userNameOfRequestee the User Name of the user being friended
	 */
	public CommandToMakeFriendRequest(long userIDOfRequester, String userNameOfRequestee)
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
		PersonFinder pfinder = FinderRegistry.personFinder();
		
		Person recipient = pfinder.find(userNameOfRequestee);
		Person inquirer = pfinder.find(userIDOfRequester);
		
		inquirer.requestFriend(requestedFriend)
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
