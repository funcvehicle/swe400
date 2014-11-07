package domainModel;

import unitOfWork.UnitOfWork;

/**
 * A generic domain model
 * @author Connor Fox
 *
 */
public class DomainObject
{
	long id;
	
	/**
	 * Mark any created object new
	 */
	public void markNew()
	{
		UnitOfWork.getCurrent().registerNew(this);
	}
	
	/**
	 * Mark any touched object dirty
	 */
	public void markDirty()
	{
		UnitOfWork.getCurrent().registerDirty(this);
	}
	
	/**
	 * Mark any removed object deleted
	 */
	public void markDeleted()
	{
		UnitOfWork.getCurrent().registerDeleted(this);
	}
	
	/**
	 * Return ID
	 * @return
	 */
	public long getId()
	{
		return id;
	}
	
	/**
	 * Set the ID
	 * @param id
	 */
	public void setId(long id)
	{
		this.id = id;
	}
}
