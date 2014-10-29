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
	
	public Friend(String nameDisplay, long id)
	{
		displayName = nameDisplay;
		this.id = id;
	}
	
	public Friend(String nameDisplay, long id, long relationshipId)
	{
		this(nameDisplay, relationshipId);
		this.reationshipId = relationshipId;
	}
	
	public Friend(String nameDisplay, long id, long relationshipId, long currentUserId)
	{
		this(nameDisplay, currentUserId, relationshipId);
		this.currentUserId = currentUserId;
	}
	
	public static Friend createNewFriend(String displayName, long id, long relationshipId, long currentUserId)
	{
		Friend f = new Friend(displayName, id, relationshipId, currentUserId);
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
