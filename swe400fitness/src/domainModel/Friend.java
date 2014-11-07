package domainModel;
/**
 * Friend object is used to be stored
 * in lists and make requests
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	private String displayName;
	private long relationId;
	private long ownerId;
	private final boolean pending;
	
	/**
	 * Create a friend object to be stored in lists
	 * @param nameDisplay
	 * @param friendId
	 * @param currentUserId
	 * @param pending
	 */
	public Friend(String nameDisplay, long friendId, long currentUserId, boolean pending)
	{
		this.id = friendId;
		this.displayName = nameDisplay;
		this.ownerId = currentUserId;
		this.pending = pending;
	}
	
	/**
	 * Create a friend object to be stored in lists
	 * Add a relationship ID
	 * @param nameDisplay
	 * @param relationId
	 * @param currentUserId
	 * @param friendId
	 * @param pending
	 */
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

	/**
	 * Create a toString of the displayName
	 */
	public String toString()
	{
		return displayName;
	}
	
	/**
	 * Returns relation ID
	 * @return
	 */
	public long getRelationId()
	{
		return relationId;
	}
	
	/**
	 * Returns current displayName
	 * @return
	 */
	public String getDisplayName()
	{
		return displayName;
	}
	
	/**
	 * Return the pending status
	 * @return
	 */
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
	
	/**
	 * Returns the OwnerID
	 * @return
	 */
	public long getOwnerId()
	{
		return ownerId;
	}

	/**
	 * Set the relationship ID
	 * @param relationId
	 */
	public void setRelationId(long relationId)
	{
		this.relationId = relationId;
	}
}
