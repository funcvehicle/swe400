package mapper;

import gateway.FriendGateway;
import domainModel.DomainObject;
import domainModel.FriendList;

/**
 * 
 * @author Hayden Cook
 *
 */
public class FriendMapper implements Mapper
{
	FriendGateway friendGate;
	
	//Finds the friend from the database, loads it in, and returns a friend
	public FriendList find(long id)
	{
		RecordSet record = friendGate.find(id);
		Friend friend = load(record);
		return friend;
	}
	
	//Finds all of the information for the friend from the record set, and then loads it 
	//into a friend object
	public FriendList load(RecordSet record)
	{
		String user = record.getString("user_name");
		int friendID = record.getInt("friendID");
		Friend friend = new Friend(user, friendID);
		return friend;
	}
	
	//Updates the friend to hold more current information. Goes through Friend.
	public void update(FriendList friend)
	{
		String user = friend.getUser();
		int friendID = friend.getID();
		friendGate.update(user, friendID);
	}
	
	//Creates a friend using information gathered
	public void create(FriendList friend)
	{
		String user = friend.getUser();
		int friendID = friend.getID();
		friendGate.create(user, friendID);
	}
	
	//Deletes a friend through the friend gate
	public void delete(FriendList friend)
	{
		int friendID = friend.getID();
		friendGate.delete(friendID);
	}

	@Override
	public void insert(DomainObject o) {
		if(o instanceof Friend)
		{
			this.create((Friend)o);
		}
		
	}

	@Override
	public void update(DomainObject o) {
		if(o instanceof Friend)
		{
			this.update((Friend)o);
		}
		
	}
	
	
}
