package mapper;

import domainModel.DomainObject;

public interface Mapper
{
	public void insert(DomainObject o);
	public void update(DomainObject o);
	public void delete(DomainObject o);
}
