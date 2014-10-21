package gateway;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class testPerson 
{
	PersonGateway pg = new PersonGateway();
	
	@Test
	public void testCreatePerson() throws SQLException
	{
		assertFalse(pg.insert(-2, "testy", "testerson", "test123") == SQLEnum.FAILED_SQL_ERROR);
		ResultSet rs = pg.find(-2);
		rs.next();
		
		assertEquals(rs.getString("userName"), "testy");
		assertEquals(rs.getString("displayName"), "testerson");
		assertEquals(rs.getString("password"), "test123");
		assertEquals(rs.getLong("id"), -2);
		
		assertFalse(pg.delete(-2) == SQLEnum.FAILED_SQL_ERROR);
		rs = pg.find(-2);
		assertFalse(rs.next());
		
		pg.closeConnection();
	}
	
	@Test
	public void testUpdatePerson() throws SQLException
	{
		assertFalse(pg.insert(-2, "testy", "testerson", "test123") == SQLEnum.FAILED_SQL_ERROR);
		ResultSet rs = pg.find(-2);
		rs.next();
		
		assertEquals(rs.getString("userName"), "testy");
		assertEquals(rs.getString("displayName"), "testerson");
		assertEquals(rs.getString("password"), "test123");
		assertEquals(rs.getLong("id"), -2);
		
		assertFalse(pg.delete(-2) == SQLEnum.FAILED_SQL_ERROR);
		rs = pg.find(-2);
		assertFalse(rs.next());
		
		pg.closeConnection();
	}

}
