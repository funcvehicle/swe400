package Registry;

import mapper.FriendMapper;
import mapper.Mapper;
import mapper.PersonMapper;
import domainModel.DomainObject;
import domainModel.Friend;
import domainModel.Person;

/**
 * A place to hold commonly used objects 
 * @author Connor Fox
 * Database:
 * lsagroup4.cbzhjl6tpflt.us-east-1.rds.amazonaws.com:3306
 */
public class MapperRegistry
{
	public static ThreadLocal<MapperRegistry> current = new ThreadLocal<MapperRegistry>();
	
	protected PersonMapper pm;
	protected FriendMapper fm;
	
	/**
	 * Creates new person mapper and friend mapper
	 */
	public MapperRegistry()
	{
		pm = new PersonMapper();
		fm = new FriendMapper();
	}
	
	/**
	 * Gets the current registry
	 * @return current
	 */
	public static MapperRegistry getCurrent()
	{
		if (current.get() == null)
			newCurrent();
		return current.get();
	}
	
	/**
	 * Sets the registry as the current
	 */
	private static void newCurrent()
	{
		setCurrent(new MapperRegistry());
	}
	
	/**
	 * Sets the registry input as the current
	 * @param mr
	 */
	public static void setCurrent(MapperRegistry mr)
	{
		current.set(mr);
	}
	
	/**
	 * Gets the mapper 
	 * @param c
	 * @return m
	 */
	public Mapper getMapper(Class<? extends DomainObject> c)
	{
		Mapper m = null;
		
		if (c == Person.class)
			m = pm;
		
		else if (c == Friend.class)
			m = fm;
		
		return m;
	}
}
