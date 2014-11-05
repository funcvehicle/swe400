package domainLogic;

import unitOfWork.UnitOfWork;

/**
 * Tells the system to cancel any pending changes
 * 
 * @author merlin
 * @author Connor Fox
 */
public class CommandToCancelChanges implements Command
{
	/**
	 * Cancels all tracked changes from the current thread's unit of work.
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		UnitOfWork.getCurrent().clearAll();
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
