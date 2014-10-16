package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	String displayName;
	long ourId;
	
	public Friend(Person friend, long id)
	{
		this.ourId = id;
		id = friend.getId();
		displayName = friend.getDisplayName();
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
