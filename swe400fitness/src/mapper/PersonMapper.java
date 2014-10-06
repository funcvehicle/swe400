package mapper;

import recordSet.RecordSet;
import gateway.KeyGateway;
import gateway.PersonGateway;
import domainModel.DomainObject;
import domainModel.Person;

/**
 * 
 * @author Connor Fox
 *
 */
public class PersonMapper implements Mapper
{
	PersonGateway gate;
	KeyGateway keyGen;
	
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
		//String password = rs.getString("password");
		Person result = new Person(userName, displayName);
		
		return result;
	}
	
	@Override
	public void update(DomainObject o)
	{
		Person p = (Person) o;
		String userName = p.getUserName();
		String displayName = p.getDisplayName();
		long id = p.getId();
		
		gate.update(id, userName, displayName);
	}
	
	@Override
	public void insert(DomainObject o)
	{
		Person p = (Person) o;
		String userName = p.getUserName();
		String displayName = p.getDisplayName();
		
		long id = keyGen.generateKey();
		
		gate.insert(id, userName, displayName);
	}
	
	@Override
	public void delete(DomainObject o)
	{
		Person p = (Person) o;
		long id = p.getId();
		gate.delete(id);
	}
}
