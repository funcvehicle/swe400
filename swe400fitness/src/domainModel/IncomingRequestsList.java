package domainModel;

import java.util.ArrayList;

/**
 * @author Emily Maust, Olivia Pompa
 */
public class IncomingRequestsList
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
	
	/**
	 * find the friend that corresponds to the given user id.
	 * @param userId 
	 * @return the friend with the given 
	 */
	public Friend findId(long userId)
	{
		Friend friend = null;
		
		for (Friend f : incomingRequestsList)
		{
			if (f.getId() == userId)
			{
				friend = f;
				break;
			}
		}
		
		return friend;
	}
	
	public boolean removeIncomingRequest(Friend request)
	{
		request.markDeleted();
		for (int i = 0; i < incomingRequestsList.size(); i++)
		{
			if (incomingRequestsList.get(i).getRelationId() == (request.getRelationId()))
			{
				incomingRequestsList.remove(i);
				return true;
			}
		}
		return false;
	}
}
