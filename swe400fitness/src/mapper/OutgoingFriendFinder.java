package mapper;

import domainModel.OutgoingRequestsList;

public interface OutgoingFriendFinder
{
	public OutgoingRequestsList findOutgoingRequests(long id);
}
