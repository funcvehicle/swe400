package domainModel;

import unitOfWork.UnitOfWork;

/**
 * 
 * @author Connor Fox
 *
 */
public class DomainObject
{
	protected void markNew()
	{
		UnitOfWork.getCurrent().registerNew(this);
	}
	
	protected void markDirty()
	{
		UnitOfWork.getCurrent().registerDirty(this);
	}
	
	protected void markDeleted()
	{
		UnitOfWork.getCurrent().registerDeleted(this);
	}
}
