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
	
	public Friend(String nameDisplay, long id, long relationshipId)
	{
		displayName = nameDisplay;
		this.id = id;
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
