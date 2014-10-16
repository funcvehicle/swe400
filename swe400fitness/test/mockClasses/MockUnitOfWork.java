package mockClasses;

import java.util.ArrayList;

import unitOfWork.UnitOfWork;
import domainModel.DomainObject;

public class MockUnitOfWork extends UnitOfWork
{	
	public ArrayList<DomainObject> getNewList()
	{
		return newObjects;
	}
	
	public ArrayList<DomainObject> getDirtyList()
	{
		return dirtyObjects;
	}
	
	public ArrayList<DomainObject> getDeletedList()
	{
		return deletedObjects;
	}
}
