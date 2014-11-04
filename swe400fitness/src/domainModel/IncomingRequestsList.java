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
	private ArrayList<Friend> incomingRequestsList;

	public IncomingRequestsList()
	{
		incomingRequestsList = new ArrayList<Friend>();
	}
	
	public String toString()
	{
		String list = "";
		for (Friend f : incomingRequestsList)
		{
			list += f.toString() + " ";
		}
		
		return list;
	}
	
	public ArrayList<Friend> getIncomingRequestsList()
	{
		return incomingRequestsList;
	}

	public void addIncomingRequest(Friend request)
	{
		incomingRequestsList.add(request);
	}
	
	public boolean removeIncomingRequest(Friend request)
	{
		request.markDeleted();
		for (int i = 0; i < incomingRequestsList.size(); i++)
		{
			if (incomingRequestsList.get(i).getId() == (request.getId()))
			{
				incomingRequestsList.remove(i);
				return true;
			}
		}
		return false;
	}
}
