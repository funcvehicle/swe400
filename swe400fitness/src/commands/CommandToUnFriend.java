package commands;

import mapper.FriendMapper;
import mapper.MapperRegistry;
import mapper.PendingFriendMapper;
import mapper.PersonMapper;
import domainModel.Friend;
import domainModel.PendingRequest;
import domainModel.Person;

/**
 * Cancels a friend request between two users
 * @author merlin
 *
 */
public class CommandToUnFriend implements Command
{

	private long userIDOfRequester;
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
		FriendMapper fm = (FriendMapper) mapperRegistry.getMapper(Friend.class);
		
		Person me = mapper.find(userIDOfRequester);
		Person myFriend = mapper.find(userNameOfRequestee);
		
		Friend friendship = null;
		
		for (Friend friend : fm.findFriends(userIDOfRequester).getListOfFriends())
		{
			if (friend.getId() == myFriend.getId())
			{
				friendship = friend;
			}
		}
		
		if (friendship != null)
		{
			fm.delete(friendship);
		}
		
		else
		{
			System.err.println("Could not unfriend " + userNameOfRequestee + ", user is not a friend of " + me.getDisplayName());
		}
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
