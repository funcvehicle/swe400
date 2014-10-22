package domainModel;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
public class Friend extends DomainObject
{
	private String displayName;
	
	public Friend(String nameDisplay, long id)
	{
		displayName = nameDisplay;
		this.id = id;
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
