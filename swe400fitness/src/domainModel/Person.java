package domainModel;

import gateway.KeyGateway;

/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Person extends DomainObject
{
	private String userName;
	private String displayName;
	private String password;
	private FriendList myFriends;
	private OutgoingRequestsList outgoingRequests;
	private IncomingRequestsList incomingRequests;
	
	public Person(String userName, String displayName)
	{
		this(userName, displayName, new KeyGateway().generateKey());		
	}
	
	public Person(String userName, String displayName, long id)
	{
		this.userName = userName;
		this.displayName = displayName;
		outgoingRequests = new OutgoingRequestsList();
		incomingRequests = new IncomingRequestsList();
		myFriends = new FriendList();
		this.id = id;
	}

	public String toString()
	{
		return (userName + ":" + password + ":" + displayName);
	}
	
	public void setPassword(String password)
	{
		markDirty();
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
	
	public void changePassword(String newPassword)
	{
		markDirty();
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
		if (requestedFriend.getId() != this.id)
		{
			requestedFriend.addPersonToPending(this.asFriend());
			outgoingRequests.addPerson(requestedFriend.asFriend());			
		}
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
	 * Accept an incoming friend request.
	 * @param friendAccepted
	 */
	public boolean acceptRequest(Person friendAccepted)
	{
		myFriends.addFriend(friendAccepted.asFriend());
		boolean mySuccess = incomingRequests.removeRequest(friendAccepted.asFriend());
				
		friendAccepted.myFriends.addFriend(this.asFriend());
		boolean theirSuccess = friendAccepted.outgoingRequests.removeRequest(this.asFriend());
		if (mySuccess && theirSuccess == true)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Reject an incoming friend request
	 * @param requestor
	 * @return
	 */
	public boolean rejectRequest(Person requestor)
	{
		boolean mySuccess = incomingRequests.removeRequest(requestor.asFriend());	
		boolean theirSuccess = requestor.outgoingRequests.removeRequest(this.asFriend());
		
		if (mySuccess && theirSuccess == true)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Unfriend someone
	 * @param friend
	 * @return
	 */
	public boolean removeFriend(Person friend)
	{
		boolean mySuccess = myFriends.unFriend(friend.asFriend());
		boolean theirSuccess = friend.myFriends.unFriend(this.asFriend());
		if (mySuccess && theirSuccess == true)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Create an instance of myself as a friend
	 * @return
	 */
	public Friend asFriend()
	{
		return new Friend(this,this.id);
	}	
}
