package mapper;

import domainModel.OutgoingRequestsList;

public interface OutgoingFriendFinder
{
	public OutgoingRequestsList findRequests(long id);
}
