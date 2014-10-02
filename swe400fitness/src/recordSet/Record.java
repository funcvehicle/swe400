package recordSet;

import java.util.HashMap;

/**
 * 
 * @author Connor Fox
 *
 */
public class Record
{
	HashMap<String, Object> fields;
	
	public Record()
	{
		fields = new HashMap<String, Object>();
	}
	
	public void put(String key, Object value)
	{
		fields.put(key, value);
	}
	
	public String getString(String field)
	{
		return fields.get(field).toString();
	}
	
	public long getLong(String field)
	{
		return (Long) fields.get(field);
	}
	
	public int getInt(String field)
	{
		return (Integer) fields.get(field);
	}
}
