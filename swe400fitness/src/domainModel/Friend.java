package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	private String displayName;
	private long relationId;
	private long ownerId;
	private final boolean pending;
	
	public Friend(String nameDisplay, long friendId, long currentUserId, boolean pending)
	{
		this.id = friendId;
		this.displayName = nameDisplay;
		this.ownerId = currentUserId;
		this.pending = pending;
	}
	
	public Friend(String nameDisplay, long relationId, long currentUserId, long friendId, boolean pending)
	{
		this(nameDisplay, friendId, currentUserId, pending);
		this.relationId = relationId;
	}
	
	/**
	 * Mark this friendship for deletion with the unit of work.
	 */
	public void deleteMe()
	{
		this.markDeleted();
	}

	public String toString()
	{
		return displayName;
	}
	
	public long getRelationId()
	{
		return relationId;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public boolean getPending()
	{
		return pending;
	}
	
//	/**
//	 * Sets pending to false. Represents a confirmed friendship.
//	 */
//	public void confirm()
//	{
//		pending = false;
//	}
	
	public long getOwnerId()
	{
		return ownerId;
	}

	public void setRelationId(long relationId)
	{
		this.relationId = relationId;
	}
}
