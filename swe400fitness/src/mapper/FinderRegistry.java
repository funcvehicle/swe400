package mapper;

/**
 * 
 * @author Connor Fox
 *
 */
public class FinderRegistry
{
	protected PersonFinder personFinder = new PersonFinder();
	protected FriendFinder friendFinder = new FriendFinder();
	protected IncomingFriendFinder incomingFriendFinder = new IncomingFriendFinder();
	
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
