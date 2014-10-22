package commands;

import mapper.MapperRegistry;
import mapper.PersonMapper;
import domainModel.Person;

/**
 * Cancels a friend request between two users
 * @author merlin
 *
 */
public class CommandToUnFriend implements Command
{

	private int userIDOfRequester;
	private String userNameOfRequestee;


	/**
	 * 
	 * @param userIDOfRequester the User ID of the user cancel the relationship
	 * @param userNameOfRequestee the User Name of the user being unfriended
	 */
	public CommandToUnFriend(int userIDOfRequester, String userNameOfRequestee)
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
		Person requester = mapper.find(userIDOfRequester);
		Person requestee = mapper.find(userNameOfRequestee);
		requestee.rejectRequest(requester);
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
