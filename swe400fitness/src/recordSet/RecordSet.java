package recordSet;

import java.util.LinkedList;

/**
 * 
 * @author Connor Fox
 *
 */
public class RecordSet
{
	LinkedList<Record> records;
	Record current;

	public RecordSet()
	{
		records = new LinkedList<Record>();
		current = records.iterator().next();
	}
	
	public RecordSet(LinkedList<Record> l)
	{
		records = l;
		current = records.iterator().next();
	}
	
	public void addRecord(Record r)
	{
		records.add(r);
	}
	
	public String getString(String field)
	{
		return current.getString(field);
	}
	
	public long getLong(String field)
	{
		return current.getLong(field);
	}
	
	public int getInt(String field)
	{
		return current.getInt(field);
	}
	
	public void next()
	{
		current = records.iterator().next();
	}
}
