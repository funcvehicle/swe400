package domainLogic;

import gateway.PersonGateway;
import mapper.MapperRegistry;
import mapper.PendingFriendMapper;
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
		MapperRegistry mapperRegistry = MapperRegistry.getCurrent();
		PersonMapper mapper = (PersonMapper) mapperRegistry.getMapper(Person.class);
		PendingFriendMapper pfm = (PendingFriendMapper) mapperRegistry.getMapper(PendingRequest.class);
		
		Person recipient = mapper.find(userNameOfRequestee);
		System.out.println(userNameOfRequestee + " " + recipient);
		Person inquirer = mapper.find(userIDOfRequester);
		System.out.println(userIDOfRequester + " " + inquirer);
		
		PendingRequest request = pfm.create(inquirer.getId(), recipient.getId(), recipient.getDisplayName());
		
		OutgoingRequestsList ogList = pfm.findOutgoingRequests(userIDOfRequester);
		boolean valid = true;
		for (PendingRequest pr : ogList.getOutgoingRequestsList())
		{
			if (recipient.getId() == pr.getId())
				valid = false;
		}
		if (recipient.getId() == inquirer.getId())
			valid = false;
		if (valid)
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
