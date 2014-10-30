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
			list += f.toString() + " ";
		}
		
		return list;
	}
	
	public boolean addFriend(Friend friend)
	{
		return listOfFriends.add(friend);
	}
	
	public boolean unFriend(Friend friend)
	{
		friend.markDeleted();
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

	public ArrayList<Friend> getListOfFriends()
	{
		return listOfFriends;
	}
	
}
