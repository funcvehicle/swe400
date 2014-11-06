package mapper;

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
	@Test
	public void testPersonMapper()
	{
		MapperRegistry.getCurrent().getMapper(Person.class);
	}
}
