package mapper;

import gateway.PersonGateway;
import domainModel.Person;

/**
 * 
 * @author Connor Fox
 *
 */
public class PersonMapper
{
	PersonGateway gate;
	
	public Person find(long id)
	{
		RecordSet rs = gate.find(id);
		rs.next();
		return load(rs);
	}
	
	private Person load(RecordSet rs)
	{
		String userName = rs.getString("username");
		String displayName = rs.getString("display_name");
		Person result = new Person(userName, displayName);
		return result;
	}
	
	public void update(Person p)
	{
		String userName = p.getUserName();
		String displayName = p.getDisplayName();
		long id = p.getId();
		
		gate.update(id, userName, displayName);
	}
	
	public void create(Person p)
	{
		String userName = p.getUserName();
		String displayName = p.getDisplayName();
		long id = p.getId();
		
		gate.insert(id, userName, displayName);
	}
}
