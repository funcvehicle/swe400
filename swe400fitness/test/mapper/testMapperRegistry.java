package mapper;

import java.sql.SQLException;

import gateway.FriendGateway;
import gateway.KeyGateway;
import gateway.PendingFriendGateway;
import gateway.PersonGateway;
import mockClasses.MockFriendGateway;
import mockClasses.MockKeyGateway;
import mockClasses.MockPendingFriendGateway;
import mockClasses.MockPersonGateway;

import org.junit.Before;
import org.junit.Test;

import Registry.MapperRegistry;
import domainModel.Person;

/**
 * 
 * @author Connor Fox
 *
 */
public class testMapperRegistry
{
	PersonMapper map;
	
	@Before
	public void preStuff() throws SQLException
	{
		FriendGateway friendGate = new MockFriendGateway();
		PersonGateway personGate = new MockPersonGateway();
		PendingFriendGateway pendingGate = new MockPendingFriendGateway();
		KeyGateway keyGate = new MockKeyGateway();
		map = new PersonMapper(personGate, friendGate, pendingGate, keyGate);
	}
	
	@Test
	public void testPersonMapper()
	{
		MapperRegistry.getCurrent().getMapper(Person.class);
	}
}
