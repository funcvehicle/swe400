package domainModel;

import java.util.ArrayList;

/**
 * This will change - Be combined with Incoming list
 * 
 * You send them a request
 * @author Emily Maust, Olivia Pompa
 */

public class OutgoingRequestsList
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

	public void addOutgoingRequest(Friend pendingFriend)
	{
		outgoingRequestsList.add(pendingFriend);
	}

	public boolean removeOutgoingRequest(Friend request)
	{
		request.markDeleted();
		for (int i = 0; i < outgoingRequestsList.size(); i++)
		{
			if (outgoingRequestsList.get(i).getRelationId() == (request.getRelationId()))
			{
				outgoingRequestsList.remove(i);
				return true;
			}
		}
		return false;
	}
}
