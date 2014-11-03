package unitOfWork;

import java.util.ArrayList;
import java.util.Iterator;

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
	public static ThreadLocal<UnitOfWork>	current	= new ThreadLocal<UnitOfWork>();

	protected ArrayList<DomainObject>		newObjects;
	protected ArrayList<DomainObject>		dirtyObjects;
	protected ArrayList<DomainObject>		deletedObjects;

	public UnitOfWork()
	{
		newObjects = new ArrayList<DomainObject>();
		dirtyObjects = new ArrayList<DomainObject>();
		deletedObjects = new ArrayList<DomainObject>();
	}

	/**
	 * @return The UnitOfWork object for the current thread.
	 */
	public static UnitOfWork getCurrent()
	{
		if (current.get() == null)
		{
			newCurrent();
		}

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
	 * 
	 * @param u The new UnitOfWork to be used.
	 */
	public static void setCurrent(UnitOfWork u)
	{
		current.set(u);
	}

	/**
	 * Add a new uncommitted in-memory object to the new objects array
	 * 
	 * @param object
	 */
	public void registerNew(DomainObject object)
	{
		newObjects.add(object);
	}

	/**
	 * If the object is not new, and is not already in dirty, add the object to
	 * the dirty list
	 * 
	 * @param object
	 */
	public void registerDirty(DomainObject object)
	{
		if (!dirtyObjects.contains(object) && !newObjects.contains(object))
		{
			dirtyObjects.add(object);
		}
	}

	/**
	 * if the object is new or dirty, remove it - else add to delete list.
	 * 
	 * @param object
	 */
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

	/**
	 * 
	 * @return
	 */
	public boolean commit()
	{
		boolean success;

		success = insertNew();
		success = updateDirty();
		success = removeDeleted();

		clearAll();

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
		ArrayList<DomainObject> toDelete = new ArrayList<DomainObject>();

		// insert people first
		for (DomainObject o : newObjects)
		{
			if (o.getClass() == Person.class)
			{
				mpr = mr.getMapper(o.getClass());
				mpr.insert(o);
				toDelete.add(o);
			}
		}
		removeFrom(toDelete, newObjects);

		for (DomainObject o : newObjects)
		{
			mpr = mr.getMapper(o.getClass());
			mpr.insert(o);
		}

		return success;
	}
	
	/**
	 * Calls the mapper's update method for all objects in the dirty list
	 * @return whether the records were successfully updated
	 */
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

	/**
	 * Calls the mapper's delete method for all objects in the deleted list.
	 * @return
	 */
	public boolean removeDeleted()
	{
		boolean success = true;
		MapperRegistry mr = MapperRegistry.getCurrent();
		Mapper mpr;
		ArrayList<DomainObject> toDelete = new ArrayList<DomainObject>();

		// delete friend records first (foreign key on person)
		for (DomainObject o : deletedObjects)
		{
			if (o.getClass() != Person.class)
			{
				mpr = mr.getMapper(o.getClass());
				mpr.delete(o);
				toDelete.add(o);
			}
		}
		removeFrom(toDelete, deletedObjects);

		for (DomainObject o : deletedObjects)
		{
			mpr = mr.getMapper(o.getClass());
			mpr.delete(o);
		}

		return success;
	}
	
	/**
	 * Remove specific objects from the target arraylist.
	 * @param source the objects you want to remove
	 * @param target the list to remove from
	 */
	private void removeFrom(ArrayList<DomainObject> source, ArrayList<DomainObject> target)
	{
		for (DomainObject o : source)
		{
			target.remove(o);
		}
		source.clear();
	}

	/**
	 * Clear all arrays; effectively cancels any in-memory changes from being
	 * committed.
	 */
	public void clearAll()
	{
		newObjects.clear();
		dirtyObjects.clear();
		deletedObjects.clear();
	}
}
