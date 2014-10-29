package domainModel;

public class PendingRequest extends DomainObject
{
	private long requesterId;
	private long requesteeId;
	
	public PendingRequest(long requesterId, long requesteeId)
	{
		this.requesterId = requesterId;
		this.requesteeId = requesteeId;
	}
	
	public long getRequesterId()
	{
		return requesterId;
	}
	
	public long getRequesteeId()
	{
		return requesteeId;
	}
}
