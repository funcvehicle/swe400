package domainModel;

import unitOfWork.UnitOfWork;

/**
 * 
 * @author Connor Fox
 *
 */
public class DomainObject
{
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
}
