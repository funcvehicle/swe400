package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	private String displayName;
	private long friendId;
	private long currentUserId;
	
	public Friend(String nameDisplay, long friendId, long currentUserId, long id)
	{
		this.id = id;
		this.displayName = nameDisplay;
		this.currentUserId = currentUserId;
		this.friendId = friendId;
		
	}
	
	public Friend(String nameDisplay, long friendId, long currentUserId)
	{
		
	}
	
	/**
	 * Create a new friend and mark it as new with the unit of work.
	 * @param displayName the friend's display name
	 * @param friendId the friend's id
	 * @param currentUserId the user who this friend is a friend of.
	 * @return a new friend object without an id
	 */
	public static Friend createNewFriend(String displayName, long friendId, long currentUserId)
	{
		Friend f = new Friend(displayName, friendId, currentUserId);
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
	
	public long getCurrentUserId()
	{
		return currentUserId;
	}
}
