package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	long id;
	String displayName;
	
	public Friend(Person friend)
	{
		id = friend.getId();
		displayName = friend.getDisplayName();
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public long getId()
	{
		return id;
	}
}
