package mapper;

import gateway.FriendGateway;
import domainModel.FriendList;

/**
 * 
 * @author Hayden Cook
 *
 */
public class FriendMapper 
{
	FriendGateway friendGate;
	
	public FriendList find(long id)
	{
		RecordSet record = friendGate.find(id);
		FriendList friend = load(record);
		return friend;
	}
	
	public FriendList load(RecordSet record)
	{
		String user = record.getString("user_name");
		int friendID = record.getInt("friendID");
		FriendList friend = new FriendList(user, friendID);
		return friend;
	}
	
	public void update(FriendList friend)
	{
		String user = friend.getUser();
		int friendID = friend.getID();
		friendGate.update(user, friendID);
	}
	
	public void create(FriendList friend)
	{
		String user = friend.getUser();
		int friendID = friend.getID();
		friendGate.create(user, friendID);
	}
	
	public void delete(FriendList friend)
	{
		int friendID = friend.getID();
		friendGate.delete(friendID);
	}
	
	
}
