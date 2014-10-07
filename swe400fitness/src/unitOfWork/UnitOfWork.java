package unitOfWork;

import java.util.ArrayList;

import mapper.Mapper;
import mapper.MapperRegistry;

import domainModel.DomainObject;
import domainModel.Person;

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
	 * @return
	 */
	public boolean insertNew()
	{
		boolean success = true;
		Mapper mpr;
		MapperRegistry mr = MapperRegistry.getCurrent();
		
		//insert people first
		for (DomainObject o : newObjects)
		{
			if (o.getClass() == Person.class)
			{
				mpr = mr.getMapper(o.getClass());
				mpr.insert(o);
				newObjects.remove(o);
			}
		}
		
		for (DomainObject o : newObjects)
		{
			mpr = mr.getMapper(o.getClass());
			mpr.insert(o);
		}
		
		return success;
	}
	
	public boolean updateDirty()
	{
		boolean success = true;
		Mapper mpr;
		MapperRegistry mr = MapperRegistry.getCurrent();
		
		for (DomainObject o : dirtyObjects)
		{
			mpr = mr.getMapper(o.getClass());
			mpr.update(o);
		}
		
		return success;
	}
	
	public boolean removeDeleted()
	{
		boolean success = true;
		MapperRegistry mr = MapperRegistry.getCurrent();
		Mapper mpr;
		
		//delete friend records first (foreign key on person)
		for (DomainObject o : deletedObjects)
		{
			if (o.getClass() != Person.class)
			{
				mpr = mr.getMapper(o.getClass());
				mpr.delete(o);
				deletedObjects.remove(o);
			}
		}
		
		for (DomainObject o : deletedObjects)
		{
			mpr = mr.getMapper(o.getClass());
			mpr.delete(o);
		}
		
		return success;
	}
	
	/**
	 * Clear all arrays; effectively cancels any in-memory changes from being committed.
	 */
	public void clearAll()
	{
		newObjects.clear();
		dirtyObjects.clear();
		deletedObjects.clear();
	}
}
