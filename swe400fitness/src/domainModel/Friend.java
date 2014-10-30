package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	private String displayName;
	private long reationshipId;
	private long currentUserId;
	
	public Friend(String nameDisplay, long friendId)
	{
		displayName = nameDisplay;
		this.id = friendId;
	}
	
	public Friend(String nameDisplay, long friendId, long relationshipId)
	{
		this(nameDisplay, friendId);
		this.reationshipId = relationshipId;
	}
	
	public Friend(String nameDisplay, long friendId, long relationshipId, long currentUserId)
	{
		this(nameDisplay, friendId, relationshipId);
		this.currentUserId = currentUserId;
	}
	
	public static Friend createNewFriend(String displayName, long friendId, long relationshipId, long currentUserId)
	{
		Friend f = new Friend(displayName, friendId, relationshipId, currentUserId);
		f.markNew();
		return f;
	}

	public String toString()
	{
		return displayName;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public long getRelationshipId()
	{
		return reationshipId;
	}
	
	public long getCurrentUserId()
	{
		return currentUserId;
	}
}
