package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	private String displayName;
	long ourId;
	
	public Friend(Person friend, long id)
	{
		this.ourId = id;
		id = friend.getId();
		displayName = friend.getDisplayName();
		this.id = friend.id;
	}

	public String toString()
	{
		return displayName;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	public long getCurrentUserID()
	{
		return this.ourId;
	}
}
