package mapper;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 
 * @author Connor Fox
 *
 */
public class RecordSet
{
	LinkedList<HashMap<String, Object>> records;
	HashMap<String, Object> current;

	public RecordSet()
	{
		records = new LinkedList<HashMap<String, Object>>();
		current = records.iterator().next();
	}
	
	public String getString(String field)
	{
		return (String) current.get(field);
	}
	
	public long getLong(String field)
	{
		return (long) current.get(field);
	}
	
	public int getInt(String field)
	{
		return (int) current.get(field);
	}
	
	public void next()
	{
		current = records.iterator().next();
	}

	public boolean getBoolean(String field)
	{
		int i = (int) current.get(field);
		boolean result = false;
		if (i == 0)
			result = false;
		else if (i == 1)
			result = true;
		return result;
	}
}
