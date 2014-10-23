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
	PersonGateway	gate;
	KeyGateway		keyGen;

	public PersonMapper(PersonGateway gate)
	{
		gate = new PersonGateway();
	}

	/**
	 * Create a new person and fetch an id for him/her
	 * @param userName
	 * @param password
	 * @param displayName
	 * @return
	 */
	public Person create(String userName, String password, String displayName)
	{
		long id = keyGen.generateKey();
		Person p = Person.createNewPerson(userName, displayName, id);
		p.setPassword(password);
		
		return p;
	}

	/**
	 * Find a person with the given id.
	 * @param id
	 * @return
	 */
	public Person find(long id)
	{
		ResultSet rs = gate.find(id);
		try
		{
			rs.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return load(rs);
	}

	/**
	 * Find a person with the given username.
	 * @param username
	 * @return
	 */
	public Person find(String username)
	{
		ResultSet rs = gate.find(username);
		try
		{
			rs.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return load(rs);
	}

	/**
	 * Construct a Person object from a ResultSet.
	 * @param rs result to use to construct the person
	 * @return a constructed person object
	 */
	private Person load(ResultSet rs)
	{
		String userName, displayName;
		Person result = null;
		
		try
		{
			long id = rs.getLong("id");
			userName = rs.getString("username");
			displayName = rs.getString("display_name");
			
			result = new Person(userName, displayName, id);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Update the person's record in the people table.
	 */
	@Override
	public void update(DomainObject o)
	{
		Person p = (Person) o;
		String userName = p.getUserName();
		String password = p.getPassword();
		String displayName = p.getDisplayName();
		long id = p.getId();

		gate.update(id, userName, password, displayName);
	}

	/**
	 * Insert a person into the people table.
	 */
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

	/**
	 * Delete the person from the people table
	 * --Should never be called.
	 */
	@Override
	public void delete(DomainObject o)
	{
		Person p = (Person) o;
		long id = p.getId();
		
		gate.delete(id);
	}
}
