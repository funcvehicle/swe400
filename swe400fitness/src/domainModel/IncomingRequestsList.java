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

	public void addIncomingRequest(Friend friend)
	{
		incomingRequestsList.add(friend);
	}
	
	public ArrayList<Friend> getIncomingRequestsList()
	{
		return incomingRequestsList;
	}
}
