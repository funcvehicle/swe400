package domainModel;

import java.util.ArrayList;

/**
 * Stores a list of incoming friend requests
 * from other people
 * @author Emily Maust, Olivia Pompa
 */
public class IncomingRequestsList extends DomainObject
{
	private ArrayList<Friend> incomingRequestsList;

	/**
	 * Instantiate the incomingRequestsList as an
	 * ArrayList of Friends
	 */
	public IncomingRequestsList()
	{
		incomingRequestsList = new ArrayList<Friend>();
	}
	
	/**
	 * Returns a toString list of
	 * all Friend objects in the incomingRequestsList
	 */
	public String toString()
	{
		String list = "";
		for (Friend f : incomingRequestsList)
		{
			list += f.toString() + ",";
		}
		if (!list.equals(""))
				list = list.substring(0, list.length() - 1);
		return list;
	}
	
	/**
	 * Return the incoming requests list
	 * @return ArrayList<Friend>
	 */
	public ArrayList<Friend> getIncomingRequestsList()
	{
		return incomingRequestsList;
	}

	/**
	 * Add a Friend object to the incomingRequestList
	 * @param request
	 */
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
	
	/**
	 * Remove an incoming request Friend from the list
	 * @param request
	 * @return boolean based on success
	 */
	public boolean removeIncomingRequest(Friend request)
	{
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
