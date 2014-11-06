package domainLogic;

import mapper.MapperRegistry;

public class CommandToDeleteUser implements Command
{
	private long userId;

	public CommandToDeleteUser(long userId)
	{
		this.userId = userId;
	}
	
	@Override
	public void execute()
	{
		MapperRegistry mapperRegistry = MapperRegistry.getCurrent();
	}

	@Override
	public Object getResult()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
