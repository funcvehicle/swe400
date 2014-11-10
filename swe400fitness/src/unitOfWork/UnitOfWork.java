package unitOfWork;

import gateway.ConnectionUtil;
import java.util.ArrayList;
import Registry.MapperRegistry;

import mapper.Mapper;

import domainModel.DomainObject;
import domainModel.Person;

/**
 * @author Connor Fox
 * A unit of work for tracking and committing batches of changes.
 */
public class UnitOfWork
{
	private static ThreadLocal<UnitOfWork>	current	= new ThreadLocal<UnitOfWork>();
	private ConnectionUtil conn = ConnectionUtil.getCurrent();
	
	private Person currentUser;

	protected ArrayList<DomainObject>		newObjects;
	protected ArrayList<DomainObject>		dirtyObjects;
	protected ArrayList<DomainObject>		deletedObjects;
	
	protected MapperRegistry registryOfMappers;

	/**
	 * Instantiate object lists.
	 */
	public UnitOfWork()
	{
		newObjects = new ArrayList<DomainObject>();
		dirtyObjects = new ArrayList<DomainObject>();
		deletedObjects = new ArrayList<DomainObject>();
		
		registryOfMappers = MapperRegistry.getCurrent();
	}
	
	public void setCurrentUser(Person p)
	{
		currentUser = p;
	}
	
	public Person getCurrentUser()
	{
		return currentUser;
	}

	/**
	 * Retrieve the current unit of work for this thread.
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
	 * @param u The new UnitOfWork to be used.
	 */
	public static void setCurrent(UnitOfWork u)
	{
		current.set(u);
	}

	/**
	 * Add a new uncommitted in-memory object to the new objects array
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
	 * Commits the changes to the database
	 * @return whether the commit was able to complete all actions successfully
	 */
	public boolean commit()
	{
		boolean success;
		
		conn.open();
		conn.setDoNotClose(true);

		success = insertNew();
		success = updateDirty();
		success = removeDeleted();
		
		conn.setDoNotClose(false);
		conn.close();

		clearAll();

		return success;
	}

	/**
	 * Calls on the object's mappers to insert records into the database
	 * @return true if every insert was completed successfully
	 */
	public boolean insertNew()
	{
		boolean success = true;
		Mapper mpr;

		// insert people first
		for (int i = 0; i < newObjects.size(); i++)
		{
			DomainObject o = newObjects.get(i);
			if (o.getClass() == Person.class)
			{
				mpr = registryOfMappers.getMapper(o.getClass());
				mpr.insert(o);
				newObjects.remove(o);
			}
		}

		for (DomainObject o : newObjects)
		{
			mpr = registryOfMappers.getMapper(o.getClass());
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

		for (DomainObject o : dirtyObjects)
		{
			mpr = registryOfMappers.getMapper(o.getClass());
			mpr.update(o);
		}

		return success;
	}

	/**
	 * Calls the mapper's delete method for all objects in the deleted list.
	 * @return true if everything was deleted successfully
	 */
	public boolean removeDeleted()
	{
		boolean success = true;
		Mapper mpr;

		// delete records that aren't a person first (foreign key on person)
		for (int i = 0; i < deletedObjects.size(); i++)
		{
			DomainObject o = deletedObjects.get(i);
			if (o.getClass() != Person.class)
			{
				mpr = registryOfMappers.getMapper(o.getClass());
				mpr.delete(o);
				deletedObjects.remove(o);
			}
		}

		for (DomainObject o : deletedObjects)
		{
			mpr = registryOfMappers.getMapper(o.getClass());
			mpr.delete(o);
		}

		return success;
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
