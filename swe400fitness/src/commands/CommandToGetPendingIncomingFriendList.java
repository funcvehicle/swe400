package commands;

import java.util.ArrayList;

import mapper.MapperRegistry;
import mapper.PersonMapper;
import domainModel.Friend;
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

	private int userID;
	private ArrayList<Friend> incomingFriendsList;

	/**
	 * The userID of the current user
	 * 
	 * @param userID
	 *            unique
	 */
	public CommandToGetPendingIncomingFriendList(int userID)
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
		Person person = mapper.find(userID);
		incomingFriendsList = person.getIncomingRequests().getIncomingRequestsList();
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
