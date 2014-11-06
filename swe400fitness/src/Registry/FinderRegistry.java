package Registry;

import mapper.FriendFinder;
import mapper.FriendMapper;
import mapper.IncomingFriendFinder;
import mapper.OutgoingFriendFinder;
import mapper.PersonFinder;
import mapper.PersonMapper;

/**
 * 
 * @author Connor Fox
 *
 * Allows domain logic to use a common set of finders to find needed records as objects
 */
public class FinderRegistry
{	
	protected PersonFinder personFinder = new PersonMapper();
	protected FriendFinder friendFinder = new FriendMapper();
	protected IncomingFriendFinder incomingFriendFinder = new FriendMapper();
	protected OutgoingFriendFinder outgoingFriendFinder = new FriendMapper();
	
	private static FinderRegistry soleInstance = new FinderRegistry();
	
	private static FinderRegistry getInstance()
	{
		return soleInstance;
	}
	
	public static PersonFinder personFinder()
	{
		return getInstance().personFinder;
	}
	
	public static FriendFinder friendFinder()
	{
		return getInstance().friendFinder;	
	}
	
	public static IncomingFriendFinder incomingFriendFinder()
	{
		return getInstance().incomingFriendFinder;
	}
	
	public static OutgoingFriendFinder outgoingFriendFinder()
	{
		return getInstance().outgoingFriendFinder;
	}
}
