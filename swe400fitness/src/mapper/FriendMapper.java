package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import gateway.FriendGateway;
import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.Person;

/*
 * Author: Hayden Cook
 */
public class FriendMapper implements Mapper
{
	FriendGateway friendGate;
	
	public FriendMapper(FriendGateway friendGate)
	{
		friendGate = new FriendGateway();
	}
	
	public Friend find(long id)
	{
		ResultSet record = friendGate.find(id);
		try 
		{
			record.next();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return load(record);
	}
	
	public Friend find(Long id)
	{
		ResultSet record = friendGate.find(id);
		try
		{
			record.next();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return load(record);
	}
	
	private Friend load(ResultSet record)
	{
		try {
		String userName = record.getString("username");
		String displayName = record.getString("displayname");
		Person person = new Person(userName, displayName);
		long id;
		id = record.getLong("ourId");
		Friend friend = new Friend(person, id);
		return friend;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@Override
	public void update(DomainObject object)
	{
		
	}
	
	@Override
	public void insert(DomainObject object)
	{
		//Friend friend = (Friend) object;
		//String displayName = friend.getDisplayName();
		//long id = friend.getId();
		//friendGate.insert(id, displayName);
		// TODO
	}
	
	@Override
	public void delete(DomainObject object)
	{
		//Friend friend = (Friend) object;
		//long id = friend.getId();
		// TODO Check with Gateway
		//friendGate.delete(id);
	}
}

