package mockClasses;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import com.mysql.jdbc.Statement;
import com.sun.rowset.CachedRowSetImpl;

import domainModel.Friend;
import domainModel.FriendList;
import gateway.FriendGateway;
import gateway.SQLEnum;

public class MockFriendGateway extends FriendGateway
{
	FriendList list;
	Friend friend;
	
	public MockFriendGateway()
	{
		list = new FriendList();
	}
	
	@Override
	public SQLEnum create(long relationID, long personID, long friendID)
	{
		String name = "Guy";
		friend = new Friend(name, personID, friendID);
		list.addFriend(friend);
		return null;
	}
	
	@Override
	public SQLEnum delete(long id)
	{
		list.unFriend(friend);
		return null;
	}
	
	public FriendList getFriendList()
	{
		return list;
	}
	
	
}
