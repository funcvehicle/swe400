package domainModel;

import java.util.ArrayList;

/**
 * Holds a list of all outgoing friend requests
 * to other people
 * @author Emily Maust, Olivia Pompa
 */
public class OutgoingRequestsList extends DomainObject
{
	private ArrayList<Friend> outgoingRequestsList;

	/**
	 * Instantiate the new list as an ArrayList of Friend objects
	 */
	public OutgoingRequestsList()
	{
		outgoingRequestsList = new ArrayList<Friend>();
	}
	
	public OutgoingRequestsList(ArrayList<Friend> f)
	{
		outgoingRequestsList = f;
	}
	
	/**
	 * Creates a toString list of
	 * all Friend objects in the OutgoingRequestsList
	 */
	public String toString()
	{
		String list = "";
		for (Friend f : outgoingRequestsList)
		{
			list += f.toString() + ",";
		}
		if (!list.equals(""))
			list = list.substring(0, list.length() - 1);
		return list;
	}
	
	/**
	 * Get the list
	 * @return the outgoingRequestsList
	 */
	public ArrayList<Friend> getOutgoingRequestsList()
	{
		return outgoingRequestsList;
	}

	/**
	 * Add a Friend object to the outgoing list
	 * @param pendingFriend
	 */
	public void addOutgoingRequest(Friend pendingFriend)
	{
		outgoingRequestsList.add(pendingFriend);
	}

	/**
	 * Remove the friend object from the outgoingRequestList
	 * @param request
	 * @return boolean based on success
	 */
	public boolean removeOutgoingRequest(Friend request)
	{
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
	
	/**
	 * find the friend that corresponds to the given user id.
	 * @param userId 
	 * @return the friend with the given 
	 */
	public Friend findId(long userId)
	{
		Friend friend = null;
		
		for (Friend f : outgoingRequestsList)
		{
			if (f.getId() == userId)
			{
				friend = f;
				break;
			}
		}
		return friend;
	}
	
	public OutgoingRequestsList clone()
	{
		ArrayList<Friend> clonedRequests = (ArrayList<Friend>) outgoingRequestsList.clone();
		return new OutgoingRequestsList(clonedRequests);
	}
}
