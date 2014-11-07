package domainModel;

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
	 * @deprecated Marks this person as deleted with the unit of work
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
		Friend request = requestedFriend.asRequest(this.id);
		if (requestedFriend.getId() != this.id)
		{
			request.markNew();
			this.addOutgoingRequest(request);
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
	private void addIncomingRequest(Friend friend)
	{
		incomingRequests.addIncomingRequest(friend);
	}

	/**
	 * Adds a person to my incoming requests.
	 * 
	 * @param friend
	 */
	private void addOutgoingRequest(Friend friend)
	{
		if (outgoingRequests == null)
			System.out.println("oh dear");
		outgoingRequests.addOutgoingRequest(friend);
	}

	/**
	 * Remove an incoming friend request from the incoming list.
	 * 
	 * @param request
	 * @return true if the incoming request was removed.
	 */
	private boolean removeIncomingRequest(Friend request)
	{
		boolean mySuccess = incomingRequests.removeIncomingRequest(request);
		return mySuccess;
	}

	/**
	 * Accept an incoming friend request. Delete the request from the incoming
	 * requests list and add it to the list of my friends.
	 * 
	 * @param friendAccepted
	 * @return true if the request was successfully accepted.
	 */
	public boolean acceptRequest(Person requester)
	{
		boolean success = false;
		Friend request = incomingRequests.findId(requester.getId());
		System.out.println("Relation ID: " + request.getRelationId() + " user: " + request.getDisplayName());
		if (request != null)
		{
			request.markDeleted();

			Friend friend = requester.asFriend(id);
			friend.markNew();

			boolean success1 = removeIncomingRequest(request);
			boolean success2 = addFriend(friend);
			success = success1 && success2;
		}
		
		return success;
	}

	/**
	 * Reject an incoming friend request. Delete the request from the incoming
	 * requests list.
	 * 
	 * @param pendingRequest
	 * @return true if the request was successfully rejected.
	 */
	public boolean rejectRequest(Friend request)
	{
		request.markDeleted();
		boolean mySuccess = incomingRequests.removeIncomingRequest(request);
		return mySuccess;
	}

	/**
	 * Unfriend someone
	 * 
	 * @param friend
	 * @return true if the friend was successfully removed.
	 */
	public boolean removeFriend(Person friend)
	{
		Friend f = myFriends.findId(friend.getId());
		f.markDeleted();
		boolean mySuccess = myFriends.unFriend(f);
		return mySuccess;
	}

	/**
	 * Add a new friend
	 * 
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
	 * 
	 * @return a Friend created from the fields of this person.
	 */
	public Friend asFriend(long myOwnerID)
	{
		return new Friend(displayName, this.id, myOwnerID, false);
	}

	/**
	 * Create an instance of myself as a friend request.
	 * 
	 * @param idOfMyFriend
	 * @param pending
	 * @return a pending friend created from the fields of this person
	 */
	public Friend asRequest(long myOwnerID)
	{
		return new Friend(displayName, this.id, myOwnerID, true);
	}
}
