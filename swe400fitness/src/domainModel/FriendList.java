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
	
	public String toString()
	{
		String list = "";
		for (Friend f : listOfFriends)
		{
			list += f.toString() + ",";
		}
		list = list.substring(0, list.length() - 1);
		return list;
	}
	
	public boolean addFriend(Friend friend)
	{
		return listOfFriends.add(friend);
	}
	
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

	public ArrayList<Friend> getListOfFriends()
	{
		return listOfFriends;
	}
	
}
