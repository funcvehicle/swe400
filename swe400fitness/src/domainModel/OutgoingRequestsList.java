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
	
	public ArrayList<Friend> getOutgoingRequestsList()
	{
		return outgoingRequestsList;
	}

	public void addPerson(Friend requestedFriend)
	{
		outgoingRequestsList.add(requestedFriend);
	}

	public boolean removeRequest(Friend friend)
	{
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
