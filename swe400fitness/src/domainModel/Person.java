package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Person extends DomainObject
{
	private long id;
	private String userName;
	private String displayName;
	private String password;
	private FriendList myFriends;
	private OutgoingRequestsList outgoingRequests;
	private IncomingRequestsList incomingRequests;
	
	public Person(String userName, String displayName)
	{
		this.userName = userName;
		this.displayName = displayName;
		outgoingRequests = new OutgoingRequestsList();
		incomingRequests = new IncomingRequestsList();
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getUserName()
	{
		return userName;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public long getId()
	{
		return id;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public IncomingRequestsList getIncomingRequests()
	{
		return incomingRequests;
	}

	public OutgoingRequestsList getOutgoingRequests()
	{
		return outgoingRequests;
	}
	
	public void getFriendsList()
	{
		myFriends.getFriendsList();
	}
	
	public void changePassword(String newPassword)
	{
		password = newPassword;
	}

	/**
	 * I will request to be another person's friend
	 * The requested friend will add me to their
	 * pending invites list.
	 * @param requestedFriend
	 */
	public void requestFriend(Person requestedFriend)
	{
		requestedFriend.addPersonToPending(this.asFriend());
		outgoingRequests.addPerson(requestedFriend.asFriend());
	}
	
	/**
	 * Adds a person to my incoming requests.
	 * @param friend
	 */
	public void addPersonToPending(Friend friend)
	{
		incomingRequests.addIncomingRequest(friend);
	}
	
	/**
	 * Create an instance of myself as a friend
	 * @return
	 */
	public Friend asFriend()
	{
		return new Friend(this);
	}
	

}
