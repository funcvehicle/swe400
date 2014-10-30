package domainModel;

public class PendingRequest extends DomainObject
{
	private long    relationId;
	private long	inquirerId;
	private long	recipientId;
	private String  displayName; //this should be the name of the person on the opposing side of the request.

	public PendingRequest(long inquirerId, long recipientId, long relationId, String displayName)
	{
		this.inquirerId = inquirerId;
		this.recipientId = recipientId;
		this.displayName = displayName;
	}

	public static PendingRequest createNewPendingRequest(long inquirerId, long recipientId, long relationId, String displayName,
			String recipientDisplayName)
	{
		PendingRequest r = new PendingRequest(inquirerId, recipientId, relationId, displayName);
		r.markNew();
		return r;
	}

	public void deleteRequest()
	{
		this.markDeleted();
	}

	public long getInquirerId()
	{
		return inquirerId;
	}
	
	public long getRelationId()
	{
		return relationId;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}

	public long getRecipientId()
	{
		return recipientId;
	}
}
