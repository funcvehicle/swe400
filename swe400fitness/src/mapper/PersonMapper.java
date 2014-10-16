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
			userName = rs.getString("username");
			displayName = rs.getString("display_name");
			Person result = new Person(userName, displayName);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String password = rs.getString("password");
		
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
