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
	private ArrayList<PendingRequest> outgoingRequestsList;

	public OutgoingRequestsList()
	{
		outgoingRequestsList = new ArrayList<PendingRequest>();
	}
	
	public String toString()
	{
		String list = "";
		for (PendingRequest f : outgoingRequestsList)
		{
			list += f.toString() + " ";
		}
		
		return list;
	}
	
	public ArrayList<PendingRequest> getOutgoingRequestsList()
	{
		return outgoingRequestsList;
	}

	public void addPerson(PendingRequest pendingFriend)
	{
		markDirty();
		outgoingRequestsList.add(pendingFriend);
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
