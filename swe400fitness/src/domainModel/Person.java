package domainModel;

import mapper.FinderRegistry;
import mapper.PersonFinder;

/**
 * 
 * @author Emily Maust, Olivia Pompa
 * 
 */
public class Person extends DomainObject
{
	private String					userName;
	private String					displayName;
	private String					password;
	private FriendList				myFriends;
	private OutgoingRequestsList	outgoingRequests;
	private IncomingRequestsList	incomingRequests;

	public Person(String userName, String displayName, String password)
	{
		this.userName = userName;
		this.displayName = displayName;
		this.password = password;
		outgoingRequests = new OutgoingRequestsList();
		incomingRequests = new IncomingRequestsList();
		myFriends = new FriendList();
	}

	public Person(String userName, String displayName, String password, long id)
	{
		this(userName, displayName, password);
		this.id = id;
	}

	/**
	 * Create a new person and register it as new with the unit of work
	 * 
	 * @param userName
	 * @param displayName
	 * @return the created person
	 */
	public static Person createNewPerson(String userName, String displayName, String password)
	{
		Person p = new Person(userName, displayName, password);
		p.markNew();
		return p;
	}

	public void setFriendList(FriendList f)
	{
		myFriends = f;
	}

	public void setOutgoingRequests(OutgoingRequestsList l)
	{
		outgoingRequests = l;
	}

	public void setIncomingRequests(IncomingRequestsList l)
	{
		incomingRequests = l;
	}

	/**
	 * Marks this person as deleted with the unit of work
	 */
	public void deleteMe()
	{
		markDeleted();
	}

	@Override
	public String toString()
	{
		return (userName + ":" + password + ":" + displayName);
	}

	public void setPassword(String password)
	{
		markDirty();
		this.password = password;
	}

	public void setDisplayName(String displayName)
	{
		markDirty();
		this.displayName = displayName;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getDisplayName()
	{
		return displayName;
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

	public FriendList getFriendList()
	{
		return myFriends;
	}

	/**
	 * I will request to be another person's friend The requested friend will
	 * add me to their pending invites list.
	 * 
	 * @param requestedFriend
	 */
	public void requestFriend(Person requestedFriend)
	{
		if (requestedFriend.getId() != this.id)
		{
			requestedFriend.addIncomingRequest(this.asFriend(requestedFriend.getId()));
			this.addOutgoingRequest(requestedFriend.asFriend(this.getId()));
		}
		else
		{
			System.err.println("User " + requestedFriend.getUserName() + " cannot request self as friend!");
		}
	}

	/**
	 * Adds a person to my incoming requests.
	 * 
	 * @param friend
	 */
	public void addIncomingRequest(Friend friend)
	{
		incomingRequests.addIncomingRequest(friend);
	}

	/**
	 * Adds a person to my incoming requests.
	 * 
	 * @param friend
	 */
	public void addOutgoingRequest(Friend friend)
	{
		outgoingRequests.addOutgoingRequest(friend);
	}

	/**
	 * Accept an incoming friend request.
	 * 
	 * @param friendAccepted
	 */
	public boolean acceptRequest(Friend request) //TODO
	{
		// find the other person so we can modify their lists also.
		PersonFinder finder = FinderRegistry.personFinder();
		Person otherPerson = finder.find(request.getOwnerId());

		// delete the request from both users correct lists.
		boolean mySuccess = incomingRequests.removeIncomingRequest(request);
		boolean theirSuccess = otherPerson.getOutgoingRequests().removeOutgoingRequest(request);
		
		//Add the friendRequest
		boolean friendSuccess = 

		return mySuccess && theirSuccess;
	}

	/**
	 * Reject an incoming friend request
	 * 
	 * @param pendingRequest
	 * @return
	 */
	public boolean rejectRequest(Friend request) //TODO
	{
		// find the other person so we can modify their lists also.
		PersonFinder finder = FinderRegistry.personFinder();
		Person otherPerson = finder.find(request.getOwnerId());

		// delete the request from both users correct lists.
		boolean mySuccess = incomingRequests.removeIncomingRequest(request);
		boolean theirSuccess = otherPerson.getOutgoingRequests().removeOutgoingRequest(request);

		return mySuccess && theirSuccess;
	}

	/**
	 * Unfriend someone
	 * 
	 * @param friend
	 * @return
	 */
	public boolean removeFriend(Friend friend)
	{
		boolean mySuccess = myFriends.unFriend(friend);

		if (mySuccess /* && theirSuccess */== true)
		{
			return true;
		}
		return false;
	}

	public boolean addFriend(Friend friend)
	{

	}

	/**
	 * Create an instance of myself as a friend.
	 * 
	 * @return a Friend created from the fields of this person.
	 */
	public Friend asFriend(long idOfMyFriend)
	{
		return new Friend(displayName, this.id, idOfMyFriend);
	}
}
