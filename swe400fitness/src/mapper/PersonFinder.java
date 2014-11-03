package mapper;

import domainModel.Person;

/**
 * 
 * @author Connor Fox
 *
 */
public interface PersonFinder
{
	public Person find(long userID);
	public Person find(String userName);
}
