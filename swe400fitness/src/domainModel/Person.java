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
	 * @deprecated
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
	 * @param requestedFriend
	 */
	public void requestFriend(Person requestedFriend)
	{
		if (requestedFriend.getId() != this.id)
		{
			this.addOutgoingRequest(requestedFriend.asFriend(this.getId(), true));
		}
		else
		{
			System.err.println("User " + requestedFriend.getUserName() + " cannot request self as friend!");
		}
	}

	/**
	 * Adds a person to my incoming requests.
	 * @param friend
	 */
	public void addIncomingRequest(Friend friend)
	{
		incomingRequests.addIncomingRequest(friend);
	}

	/**
	 * Adds a person to my incoming requests.
	 * @param friend
	 */
	public void addOutgoingRequest(Friend friend)
	{
		outgoingRequests.addOutgoingRequest(friend);
	}
	
	/**
	 * Remove an incoming friend request from the incoming list.
	 * @param request
	 * @return true if the incoming request was removed.
	 */
	public boolean removeIncomingRequest(Friend request)
	{
		boolean mySuccess = incomingRequests.removeIncomingRequest(request);
		return mySuccess;
	}

	/**
	 * Accept an incoming friend request. Delete the request from the incoming
	 * requests list and add it to the list of my friends.
	 * @param friendAccepted
	 * @return true if the request was successfully accepted.
	 */
	public boolean acceptRequest(Friend request)
	{
		boolean success1 = removeIncomingRequest(request);
		boolean success2 = addFriend(request);
		return success1 && success2;
	}

	/**
	 * Reject an incoming friend request. Delete the request from the
	 * incoming requests list.
	 * @param pendingRequest
	 * @return true if the request was successfully rejected.
	 */
	public boolean rejectRequest(Friend request)
	{
		boolean mySuccess = incomingRequests.removeIncomingRequest(request);
		return mySuccess;
	}

	/**
	 * Unfriend someone
	 * @param friend
	 * @return true if the friend was successfully removed. 
	 */
	public boolean removeFriend(Friend friend)
	{
		boolean mySuccess = myFriends.unFriend(friend);
		return mySuccess;
	}

	/**
	 * Add a new friend
	 * @param friend
	 * @return true if the friend was successfully added.
	 */
	public boolean addFriend(Friend friend)
	{
		boolean mySuccess = myFriends.addFriend(friend);
		return mySuccess;
	}

	/**
	 * Create an instance of myself as a friend.
	 * @return a Friend created from the fields of this person.
	 */
	public Friend asFriend(long idOfMyFriend, boolean pending)
	{
		return new Friend(displayName, this.id, idOfMyFriend, pending);
	}
}
