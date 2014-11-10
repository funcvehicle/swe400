package domainLogic;

import unitOfWork.UnitOfWork;
import Registry.FinderRegistry;
import mapper.PersonFinder;
import domainModel.FriendList;
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
	private FriendList friends;

	/**
	 * The userID of the current user
	 * @param userID unique
	 */
	public CommandToRetrieveFriendList(long userID)
	{
		this.userID = userID;
	}

	/**
	 * Executes the command to retrieve a friend list of a user
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
//		PersonFinder pfinder = FinderRegistry.personFinder();
		Person person = UnitOfWork.getCurrent().getCurrentUser();
		
		friends = person.getFriendList();
	}

	/**
	 * A list of the friends associated with the given user
	 * @see Command#getResult()
	 */
	@Override
	public FriendList getResult()
	{
		return friends;
	}

}
