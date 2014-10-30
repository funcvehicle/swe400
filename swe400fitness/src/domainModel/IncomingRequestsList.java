package domainModel;

import java.util.ArrayList;

/**
 * This will change - be merged with Outgoing Requests
 * 
 * You accept their request
 * @author Emily Maust, Olivia Pompa
 */
public class IncomingRequestsList extends DomainObject
{
	private ArrayList<PendingRequest> incomingRequestsList;

	public IncomingRequestsList()
	{
		incomingRequestsList = new ArrayList<PendingRequest>();
	}
	
	public String toString()
	{
		String list = "";
		for (PendingRequest f : incomingRequestsList)
		{
			list += f.toString() + " ";
		}
		
		return list;
	}
	
	public ArrayList<PendingRequest> getIncomingRequestsList()
	{
		return incomingRequestsList;
	}

	public void addIncomingRequest(PendingRequest pendingFriend)
	{
		incomingRequestsList.add(pendingFriend);
	}
	
	public boolean removeRequest(PendingRequest pendingRequest)
	{
		markDirty();
		for (int i = 0; i < incomingRequestsList.size(); i++)
		{
			if (incomingRequestsList.get(i).getId() == (pendingRequest.getId()))
			{
				incomingRequestsList.remove(i);
				return true;
			}
		}
		return false;
	}
}
