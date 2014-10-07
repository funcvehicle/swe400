package unitOfWork;

import static org.junit.Assert.*;

import org.junit.Test;

import domainModel.Person;

public class testUnitOfWork 
{
	@Test
	public void testRegisterNew()
	{
		UnitOfWork.newCurrent();
		UnitOfWork work = UnitOfWork.getCurrent();
		Person testPerson = new Person("test", "test");
		work.registerNew(testPerson);
		assertTrue(work.newObjects.contains(testPerson));
	}

}
