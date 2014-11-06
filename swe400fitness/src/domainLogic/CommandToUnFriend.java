package domainLogic;

import Registry.FinderRegistry;
import mapper.PersonFinder;
import domainModel.Person;

/**
 * Cancels a friend request between two users
 * 
 * @author merlin
 * 
 */
public class CommandToUnFriend implements Command
{
	private long	userIDOfRequester;
	private String	userNameOfRequestee;

	/**
	 * 
	 * @param userIDOfRequester the User ID of the user cancel the relationship
	 * @param userNameOfRequestee the User Name of the user being unfriended
	 */
	public CommandToUnFriend(long userIDOfRequester, String userNameOfRequestee)
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
		PersonFinder pFinder = FinderRegistry.personFinder();
		Person me = pFinder.find(userIDOfRequester);
		Person myFriend = pFinder.find(userNameOfRequestee);

		if (!me.removeFriend(myFriend.asFriend(userIDOfRequester)))
		{
			System.err.println("Could not unfriend " + userNameOfRequestee + ", user is not a friend of "
					+ me.getDisplayName());
		}
	}

	/**
	 * Nothing needs to be retrieved from this command
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public Object getResult()
	{
		return null;
	}

}
