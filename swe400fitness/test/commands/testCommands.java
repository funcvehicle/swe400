package commands;

import static org.junit.Assert.*;
import mapper.MapperRegistry;
import mockClasses.MockMapperRegistry;
import mockClasses.MockUnitOfWork;

import org.junit.Before;
import org.junit.Test;

import domainLogic.Command;
import domainLogic.CommandToCreateUser;
import domainLogic.CommandToSelectUser;
import domainModel.Person;

public class testCommands 
{
	@Before
	public void init()
	{
		MockUnitOfWork.newCurrent();
		MapperRegistry.setCurrent(new MockMapperRegistry());
	}
	
	@Test
	public void testCreateUser()
	{
		Command createUser = new CommandToCreateUser("testy", "password", "testyMctesterson");
		createUser.execute();
		Person test = (Person) createUser.getResult();
		
		assertEquals(test.getUserName(), "testy");
		assertEquals(test.getDisplayName(), "testyMctesterson");
		assertEquals(test.getPassword(), "password");
		
		test = (Person) ((MockUnitOfWork) MockUnitOfWork.getCurrent()).getNewList().get(0);
		
		assertEquals(test.getUserName(), "testy");
	}
	
	@Test
	public void testSelectUser()
	{
		Command selectUser = new CommandToSelectUser("testy", "password");
		selectUser.execute();
		Person test = (Person) selectUser.getResult();
	}
}
