package domainLogic;

import java.util.ArrayList;

import mapper.FriendMapper;
import mapper.MapperRegistry;
import mapper.PersonMapper;
import domainModel.Friend;
import domainModel.Person;

/**
 * Cause a user's friend list to be fetched from the domain model (may or may
 * not cause reading from the DB depending on the state of the domain model
 * 
 * @author merlin
 *
 */
public class CommandToRetrieveFriendList implements Command
{

	private long userID;
	private ArrayList<Friend> friends;

	/**
	 * The userID of the current user
	 * @param userID unique
	 */
	public CommandToRetrieveFriendList(long userID)
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
		FriendMapper fm = (FriendMapper) mapperRegistry.getMapper(Friend.class);
		Person person = mapper.find(userID);
		person.setFriendList(fm.findFriends(userID));
		friends = person.getFriendList().getListOfFriends();
	}

	/**
	 * A list of the friends associated with the given user
	 * @see Command#getResult()
	 */
	@Override
	public ArrayList<Friend> getResult()
	{
		return friends;
	}

}
