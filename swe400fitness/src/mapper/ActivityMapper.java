package mapper;

import gateway.ActivityGateway;
import domainModel.Activity;

/**
 * 
 * @author Connor Fox
 *
 */
public class ActivityMapper
{
	ActivityGateway gate;
	
	public Activity find(long id)
	{
		RecordSet rs = gate.find(id);
		Activity result = load(rs);
		return result;
	}
	
	private Activity load(RecordSet rs)
	{
		long userId = rs.getLong("user_id");
		long activityId = rs.getLong("activity_id");
		String activityType = rs.getString("activity_type");
		int timeSpent = rs.getInt("time_spent");
		String dateCompleted = rs.getString("date_completed");
		
		Activity result = new Activity(userId, activityId, activityType, timeSpent, dateCompleted);
		return result;
	}
	
	public void update(Activity a)
	{
		long userId = a.getUserId();
		long activityId = a.getActivityId();
		String activityType = a.getActivityType();
		int timeSpent = a.getTimeSpent();
		String dateCompleted = a.getDateCompleted();
		
		gate.update(userId, activityId, activityType, timeSpent, dateCompleted);
	}
	
	public void delete(Activity a)
	{
		long activityId = a.getActivityId();
		gate.delete(activityId);
	}
	
	public void create(Activity a)
	{
		long userId = a.getUserId();
		long activityId = a.getActivityId();
		String activityType = a.getActivityType();
		int timeSpent = a.getTimeSpent();
		String dateCompleted = a.getDateCompleted();
		
		gate.create(userId, activityId, activityType, timeSpent, dateCompleted);
	}
}
