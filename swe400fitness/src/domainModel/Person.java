package domainModel;

/**
 * Person object holds all information about an individual user
 * @author Emily Maust, Olivia Pompa
 */
public class Person extends DomainObject
{
	private String					userName;
	private String					displayName;
	private String					password;
	private FriendList				myFriends;
	private OutgoingRequestsList	outgoingRequests;
	private IncomingRequestsList	incomingRequests;

	/**
	 * Create a new Person object with a userName, displayName, and password
	 * @param userName
	 * @param displayName
	 * @param password
	 */
	public Person(String userName, String displayName, String password)
	{
		this.userName = userName;
		this.displayName = displayName;
		this.password = password;
		outgoingRequests = new OutgoingRequestsList();
		incomingRequests = new IncomingRequestsList();
		myFriends = new FriendList();
	}

	/**
	 * Create a new Person object - new constructor includes an ID
	 * @param userName
	 * @param displayName
	 * @param password
	 * @param id
	 */
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

	/**
	 * Set the friendsList to the parameter
	 * @param f
	 */
	public void setFriendList(FriendList f)
	{
		myFriends = f;
	}

	/**
	 * Set the outgoingRequestsList to the parameter
	 * @param l
	 */
	public void setOutgoingRequests(OutgoingRequestsList l)
	{
		outgoingRequests = l;
	}

	/**
	 * Set the incomingRequestsList to the parameter
	 * @param l
	 */
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

	/**
	 * Create a toString listing user's username:password:displayName
	 */
	@Override
	public String toString()
	{
		return (userName + ":" + password + ":" + displayName);
	}

	/**
	 * Set the displayName and mark it dirty
	 * @param displayName
	 */
	public void setDisplayName(String displayName)
	{
		markDirty();
		this.displayName = displayName;
	}

	/**
	 * Get the userName
	 * @return userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Get the displayName
	 * @return displayName
	 */
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * Get the password
	 * @return password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * Get the IncomingRequestsList
	 * @return IncomingRequestsList
	 */
	public IncomingRequestsList getIncomingRequests()
	{
		return incomingRequests;
	}

	/**
	 * Get the OutgoingRequestsList
	 * @return OutgoingRequestsList
	 */
	public OutgoingRequestsList getOutgoingRequests()
	{
		return outgoingRequests;
	}

	/**
	 * Get the FriendList
	 * @return FriendList
	 */
	public FriendList getFriendList()
	{
		return myFriends;
	}

	/**
	 * I will request to be another person's friend The requested friend will
	 * add me to their pending invites list.
	 * 
	 * This will not happen if I request myself or there is a pre-existing relationship.
	 * 
	 * @param requestedFriend
	 */
	public void requestFriend(Person requestedFriend)
	{
		Friend request = requestedFriend.asRequest(this.id);
		boolean isRelationship = relationshipExists(requestedFriend);

		if (requestedFriend.getId() != this.id && isRelationship == false)
		{
			request.markNew();
			this.addOutgoingRequest(request);
			requestedFriend.addIncomingRequest(this.asFriend(id));
		}
		else
		{
			System.err.println("User " + this.displayName + " cannot request " + requestedFriend.displayName + " as friend!");
		}
	}
	
	/**
	 * Check if a relationship exists.
	 * If it does, friendship cannot be requested.
	 * @param requestedFriend
	 * @return boolean based on relationship
	 */
	private boolean relationshipExists(Person requestedFriend)
	{
		boolean isRelationship = false;
		
		if (requestedFriend.getIncomingRequests().findId(id) != null)
		{
			isRelationship = true;
		}
		
		else if (outgoingRequests.findId(requestedFriend.id) != null)
		{
			isRelationship = true;
		}
		
		else if (this.getFriendList().findId(requestedFriend.id) != null)
		{
			isRelationship = true;
		}
		
		else if (this.getIncomingRequests().findId(requestedFriend.id) != null)
		{
			isRelationship = true;
		}
		
		return isRelationship;
	}

	/**
	 * Adds a person to my incoming requests.
	 * @param friend
	 */
	private void addIncomingRequest(Friend friend)
	{
		incomingRequests.addIncomingRequest(friend);
	}

	/**
	 * Adds a person to my incoming requests.
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
	 * @param friendAccepted
	 * @return true if the request was successfully accepted.
	 */
	public boolean acceptRequest(Person requester)
	{
		boolean success = false;
		Friend request = incomingRequests.findId(requester.getId());
		if (request != null)
		{
			request.markDeleted();
			Friend friend = requester.asFriend(id);
			friend.markNew();

			boolean success1 = removeIncomingRequest(request);
			boolean success2 = addFriend(friend);
			boolean success3 = requester.addFriend(this.asFriend(id));
			success = success1 && success2 && success3;
		}
		
		return success;
	}

	/**
	 * Reject an incoming friend request. Delete the request from the incoming
	 * requests list.
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
	public Friend asFriend(long myOwnerID)
	{
		return new Friend(displayName, this.id, myOwnerID, false);
	}

	/**
	 * Create an instance of myself as a friend request.
	 * @param idOfMyFriend
	 * @param pending
	 * @return a pending friend created from the fields of this person
	 */
	public Friend asRequest(long myOwnerID)
	{
		return new Friend(displayName, this.id, myOwnerID, true);
	}
	
	@Override
	public Person clone()
	{
		return new Person(userName, displayName, password, id);
	}
}
