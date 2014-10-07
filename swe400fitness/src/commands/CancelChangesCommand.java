package commands;

import unitOfWork.UnitOfWork;

/**
 * Tells the system to cancel any pending changes
 * 
 * @author merlin
 *
 */
public class CancelChangesCommand implements Command
{

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		UnitOfWork work = UnitOfWork.getCurrent();
		work.clearAll();
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
