package domainModel;

import unitOfWork.UnitOfWork;

/**
 * 
 * @author Connor Fox
 *
 */
public class DomainObject
{
	long id;
	
	public void markNew()
	{
		UnitOfWork.getCurrent().registerNew(this);
	}
	
	public void markDirty()
	{
		UnitOfWork.getCurrent().registerDirty(this);
	}
	
	public void markDeleted()
	{
		UnitOfWork.getCurrent().registerDeleted(this);
	}
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
}
