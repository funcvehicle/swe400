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
	
	public void addFriend(Friend friend)
	{
		listOfFriends.add(friend);
	}
	
	public void unFriend(Friend friend)
	{
		listOfFriends.remove(friend);
	}

	public ArrayList<Friend> getListOfFriends()
	{
		return listOfFriends;
	}
	
}
