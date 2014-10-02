package commands;

import java.util.ArrayList;

import mapper.Mapper;
import mapper.MapperRegistry;

import domainModel.DomainObject;

/**
 * 
 * @author Connor Fox
 *
 */
public class UnitOfWork
{
	public static ThreadLocal<UnitOfWork> current = new ThreadLocal<UnitOfWork>();
	
	ArrayList<DomainObject> newObjects;
	ArrayList<DomainObject> dirtyObjects;
	ArrayList<DomainObject> deletedObjects;
	
	/**
	 * @return The UnitOfWork object for the current thread.
	 */
	public static UnitOfWork getCurrent()
	{
		return current.get();
	}
	
	/**
	 * Create a new UnitOfWork object for use by the current thread.
	 */
	public static void newCurrent()
	{
		setCurrent(new UnitOfWork());
	}
	
	/**
	 * Set the UnitOfWork object used by the current thread.
	 * @param u The new UnitOfWork to be used.
	 */
	public static void setCurrent(UnitOfWork u)
	{
		current.set(u);
	}
	
	public void registerNew(DomainObject object)
	{
		newObjects.add(object);
	}
	
	public void registerDirty(DomainObject object)
	{
		if (!dirtyObjects.contains(object) && !newObjects.contains(object))
		{
			dirtyObjects.add(object);
		}
	}
	
	public void registerDeleted(DomainObject object)
	{
		if (!newObjects.remove(object))
		{
			dirtyObjects.remove(object);
			if (!deletedObjects.contains(object))
			{
				deletedObjects.add(object);
			}
		}
	}
	
	public boolean commit()
	{
		boolean success;
		
		success = insertNew();
		success = updateDirty();
		success = removeDeleted();
		
		return success;
	}
	
	/**
	 * TODO: Person insert before friends
	 * @return
	 */
	public boolean insertNew()
	{
		boolean success = true;
		
		for (DomainObject o : newObjects)
		{
			Mapper mpr = MapperRegistry.getMapper(o.getClass());
			mpr.insert(o);
		}
		
		return success;
	}
	
	public boolean updateDirty()
	{
		boolean success = true;
		
		for (DomainObject o : dirtyObjects)
		{
			Mapper mpr = MapperRegistry.getMapper(o.getClass());
			mpr.update(o);
		}
		
		return success;
	}
	
	public boolean removeDeleted()
	{
		boolean success = true;
		
		for (DomainObject o : deletedObjects)
		{
			Mapper mpr = MapperRegistry.getMapper(o.getClass());
			mpr.delete(o);
		}
		
		return success;
	}
}
