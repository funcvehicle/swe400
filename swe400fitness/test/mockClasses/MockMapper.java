package mockClasses;

import domainModel.DomainObject;
import mapper.Mapper;

public class MockMapper implements Mapper
{
	public DomainObject find(long id)
	{
		return null;
	}
	
	@Override
	public void insert(DomainObject o) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(DomainObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DomainObject o) {
		// TODO Auto-generated method stub	
	}
}
