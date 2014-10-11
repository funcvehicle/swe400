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
	
	public ArrayList<Friend> getIncomingRequestsList()
	{
		return incomingRequestsList;
	}

	public void addIncomingRequest(Friend friend)
	{
		incomingRequestsList.add(friend);
	}
	
	public boolean removeRequest(Friend friend)
	{
		for (int i = 0; i < incomingRequestsList.size(); i++)
		{
			if (incomingRequestsList.get(i).getId() == (friend.getId()))
			{
				incomingRequestsList.remove(i);
				return true;
			}
		}
		return false;
	}
}
