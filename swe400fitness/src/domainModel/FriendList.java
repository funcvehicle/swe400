package domainModel;

import java.util.ArrayList;

/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class FriendList extends DomainObject
{
	private ArrayList<Friend> listOfFriends;
	
	public FriendList()
	{
		listOfFriends = new ArrayList<Friend>();
	}
	
	public boolean addFriend(Friend friend)
	{
		return listOfFriends.add(friend);
	}
	
	public boolean unFriend(Friend friend)
	{
//		listOfFriends.remove(friend);
		return listOfFriends.remove(friend);
	}

	public ArrayList<Friend> getListOfFriends()
	{
		return listOfFriends;
	}
	
}
