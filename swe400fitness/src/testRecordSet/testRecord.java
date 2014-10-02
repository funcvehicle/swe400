package testRecordSet;

import static org.junit.Assert.*;

import org.junit.Test;

import recordSet.Record;

/**
 * 
 * @author Connor Fox
 *
 */
public class testRecord
{
	@Test
	public void testGetLong()
	{
		Record r = new Record();
		r.put("testString", "Hello");
		r.put("testLong", 500600L);
		r.put("testInt", 10);
		
		assertEquals(r.getLong("testLong"), 500600L, 0);
	}
	
	@Test
	public void testGetInt()
	{
		Record r = new Record();
		r.put("testString", "Hello");
		r.put("testLong", 500600L);
		r.put("testInt", 10);
		
		assertEquals(r.getInt("testInt"), 10, 0);
	}
	
	@Test
	public void testGetString()
	{
		Record r = new Record();
		r.put("testString", "Hello");
		r.put("testLong", 500600L);
		r.put("testInt", 10);
		
		assertEquals(r.getString("testString"), "Hello");
	}
}
