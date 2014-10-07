package identityMap;

import java.util.ArrayList;

import domainModel.Person;

public class IdentityMap 
{
	ArrayList<Person> people;
	
	public Person getPerson(String username)
	{
		Person person = null;
		
		for (Person p : people)
		{
			if (p.getUserName().equals(username))
			{
				person = p;
				break;
			}
		}
		
		return person;
	}
}
