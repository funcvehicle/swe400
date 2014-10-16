package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	String displayName;
	
	public Friend(Person friend)
	{
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
}
