package mapper;

import domainModel.Friend;
import domainModel.FriendList;

public interface FriendFinder
{
	public FriendList findFriends(Long myId);
	//public Friend find(String userName);
}
