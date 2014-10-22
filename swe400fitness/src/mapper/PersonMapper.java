package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public PersonMapper(PersonGateway gate)
	{
		gate = new PersonGateway();
	}
	
	public Person create(String userName, String password,  String displayName)
	{
		long id = keyGen.generateKey();
		Person p = Person.createNewPerson(userName, displayName, id);
		p.setPassword(password);
		return p;
	}
	
	public Person find(long id)
	{
		ResultSet rs = gate.find(id);
		try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return load(rs);
	}
	
	public Person find(String username)
	{
		ResultSet rs = gate.find(username);
		try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return load(rs);
	}
	
	private Person load(ResultSet rs)
	{
		String userName, displayName;
		try {
			long id = rs.getLong("id");
			userName = rs.getString("username");
			displayName = rs.getString("display_name");
			Person result = new Person(userName, displayName, id);
			result.setId(id);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
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
		long id = p.getId();
		String userName = p.getUserName();
		String password = p.getPassword();
		String displayName = p.getDisplayName();
		
		gate.insert(id, userName, displayName, password);
	}
	
	@Override
	public void delete(DomainObject o)
	{
		Person p = (Person) o;
		long id = p.getId();
		gate.delete(id);
	}
}
