package commands;

import domainModel.Person;
import mapper.MapperRegistry;
import mapper.PersonMapper;

/**
 * Accept a friend request from one user to another
 * @author merlin
 *
 */
public class CommandToAcceptFriendRequest implements Command
{

	private int userIDOfRequestee;
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
		PersonMapper mapper = (PersonMapper) mapperRegistry.getMapper(Person.class);
		Person requestee = mapper.find(userIDOfRequestee);
		Person requester = mapper.find(userNameOfRequester);
		requestee.acceptRequest(requester);
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
