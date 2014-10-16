package domainModel;

import java.util.ArrayList;

/**
 * This will change - Be combined with Incoming list
 * 
 * You send them a request
 * @author Emily Maust, Olivia Pompa
 */

public class OutgoingRequestsList extends DomainObject
{
	private ArrayList<Friend> outgoingRequestsList;

	public OutgoingRequestsList()
	{
		outgoingRequestsList = new ArrayList<Friend>();
	}
	
	public String toString()
	{
		String list = "";
		for (Friend f : outgoingRequestsList)
		{
			list += f.toString() + " ";
		}
		
		return list;
	}
	
	public ArrayList<Friend> getOutgoingRequestsList()
	{
		return outgoingRequestsList;
	}

	public void addPerson(Friend requestedFriend)
	{
		markDirty();
		outgoingRequestsList.add(requestedFriend);
	}

	public boolean removeRequest(Friend friend)
	{
		markDirty();
		for (int i = 0; i < outgoingRequestsList.size(); i++)
		{
			if (outgoingRequestsList.get(i).getId() == (friend.getId()))
			{
				outgoingRequestsList.remove(i);
				return true;
			}
		}
		return false;
	}
}
