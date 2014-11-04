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
	private long currentUserId;
	
	public Friend(String nameDisplay, long relationId, long currentUserId, long friendId)
	{
		this(nameDisplay, friendId, currentUserId);
		this.relationId = relationId;
	}
	
	public Friend(String nameDisplay, long friendId, long currentUserId)
	{
		this.id = friendId;
		this.displayName = nameDisplay;
		this.currentUserId = currentUserId;
	}
	
//	/**
//	 * Create a new friend and mark it as new with the unit of work.
//	 * @param displayName the friend's display name
//	 * @param friendId the friend's id
//	 * @param currentUserId the user who this friend is a friend of.
//	 * @return a new friend object without an id
//	 */
//	public static Friend createNewFriend(String displayName, long friendId, long currentUserId)
//	{
//		Friend f = new Friend(displayName, friendId, currentUserId);
//		f.markNew();
//		
//		return f;
//	}
	
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
	
	public long getCurrentUserId()
	{
		return currentUserId;
	}
}
