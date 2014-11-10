package domainModel;

import java.util.ArrayList;

/**
 * Hold a FriendList of current accepted friends
 * @author Emily Maust, Olivia Pompa
 *
 */
public class FriendList extends DomainObject
{
	private ArrayList<Friend> listOfFriends;
	
	/**
	 * Instantiate the FriendList
	 */
	public FriendList()
	{
		listOfFriends = new ArrayList<Friend>();
	}
	
	public FriendList(ArrayList<Friend> f)
	{
		listOfFriends = f;
	}
	
	/**
	 * Create a toString that lists all friends
	 */
	public String toString()
	{
		String list = "";
		for (Friend f : listOfFriends)
		{
			list += f.toString() + ",";
		}
		if (!list.equals(""))
			list = list.substring(0, list.length() - 1);
		return list;
	}
	
	/**
	 * Add a friend to the list
	 * @param friend
	 * @return
	 */
	public boolean addFriend(Friend friend)
	{
		return listOfFriends.add(friend);
	}
	
	/**
	 * Remove a friend object from my FriendList
	 * @param friend
	 * @return
	 */
	public boolean unFriend(Friend friend)
	{
		for (int i = 0; i < listOfFriends.size(); i++)
		{
			if (listOfFriends.get(i).getId() == (friend.getId()))
			{
				listOfFriends.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Find a friend object with the friend's ID
	 * @param userId
	 * @return Friend object
	 */
	public Friend findId(long userId)
	{
		Friend friend = null;
		
		for (Friend f : listOfFriends)
		{
			if (f.getId() == userId)
			{
				friend = f;
				break;
			}
		}	
		return friend;
	}

	/**
	 * Returns the list of Friends
	 * @return
	 */
	public ArrayList<Friend> getListOfFriends()
	{
		return listOfFriends;
	}
	
	public FriendList clone()
	{
		ArrayList<Friend> clonedFriends = (ArrayList<Friend>) listOfFriends.clone();
		return new FriendList(clonedFriends);
	}
	
}
