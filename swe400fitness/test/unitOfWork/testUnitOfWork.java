package unitOfWork;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domainModel.Person;

public class testUnitOfWork 
{
	@Before
	public void init()
	{
		UnitOfWork.newCurrent();
	}
	
	@Test
	public void testRegisterNew()
	{
		UnitOfWork work = UnitOfWork.getCurrent();
		Person testPerson = new Person("test", "test", -2);
		work.registerNew(testPerson);
		assertTrue(work.newObjects.contains(testPerson));
	}

}
