package domainLogic;

import mapper.PersonFinder;
import Registry.FinderRegistry;
import unitOfWork.UnitOfWork;

/**
 * Tells the system to save any pending changes
 * 
 * @author merlin
 *
 */
public class CommandToPersistChanges implements Command
{
	/**
	 * Persists the changes stored in the unit of work to the database
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		PersonFinder p = FinderRegistry.personFinder();
		UnitOfWork work = UnitOfWork.getCurrent();
		work.commit();

		if (work.getCurrentUser() != null)
		{
			work.setCurrentUser(p.find(work.getCurrentUser().getId()));
			work.setRevert(p.find(work.getCurrentUser().getId()));
		}
	}

	/**
	 * Nothing needs to be returned here (null). The tests will retrieve
	 * anything they want to check by re-finding appropriate records
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public Object getResult()
	{
		return null;
	}

}
