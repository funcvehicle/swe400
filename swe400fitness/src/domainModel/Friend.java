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
	
	public Friend(String nameDisplay, long relationId, long currentUserId, long friendId)
	{
		this(nameDisplay, friendId, currentUserId);
		this.relationId = relationId;
	}
	
	public Friend(String nameDisplay, long friendId, long currentUserId)
	{
		this.id = friendId;
		this.displayName = nameDisplay;
		this.ownerId = currentUserId;
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
	
	public long getOwnerId()
	{
		return ownerId;
	}
}
