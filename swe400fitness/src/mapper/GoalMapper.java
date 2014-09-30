package mapper;

import gateway.GoalGateway;
import domainModel.Goal;

/**
 * 
 * @author Connor Fox
 *
 */
public class GoalMapper
{
	GoalGateway gate;
	
	public Goal find(long id)
	{
		RecordSet rs = gate.find(id);
		Goal result = load(rs);
		return result;
	}
	
	private Goal load(RecordSet rs)
	{
		long goalId = rs.getLong("goal_id");
		int requisites = rs.getInt("requisites");
		String goalType = rs.getString("goal_type");
		boolean completed = rs.getBoolean("completed");
		long userId = rs.getLong("user_id");
		
		Goal result = new Goal(goalId, requisites, goalType, completed, userId);
		return result;
	}
	
	public void update(Goal g)
	{
		long goalId = g.getGoalId();
		int requisites = g.getRequisites();
		String goalType = g.getGoalType();
		boolean completed = g.getCompleted();
		long userId = g.getUserId();
		
		gate.update(goalId, requisites, goalType, completed, userId);
	}
	
	public void delete(Goal g)
	{
		long goalId = g.getGoalId();
		gate.delete(goalId);
	}
	
	public void create(Goal g)
	{
		long goalId = g.getGoalId();
		int requisites = g.getRequisites();
		String goalType = g.getGoalType();
		boolean completed = g.getCompleted();
		long userId = g.getUserId();
		
		gate.create(goalId, requisites, goalType, completed, userId);
	}
}
