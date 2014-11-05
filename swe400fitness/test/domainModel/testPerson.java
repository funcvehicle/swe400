package domainModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unitOfWork.UnitOfWork;

public class testPerson
{
	private int id;
	
	@Before
	public void init()
	{
		UnitOfWork.newCurrent();
	}
	
	public long nextId()
	{
		long curr = id;
		id++;
		return curr;
	}
	
	@Test
	public void TestMakingAPerson()
	{
		Person p1 = new Person("cooldude5", "Jon", "pw", nextId());
		assertEquals(0, p1.id);
		assertEquals("cooldude5", p1.getUserName());
		assertEquals("Jon", p1.getDisplayName());
		assertEquals("pw", p1.getPassword());
	}
	
	@Test
	public void testMakingFriendRequest()
	{
		Person jonny = new Person("cooldude5", "Jonny", "jonnyspw", nextId());
		Person mo = new Person("blackpanda7", "Mo", "pw", nextId());
		long jonnyId = jonny.id;
		long moId = mo.id;
		PendingRequest request = new PendingRequest(jonnyId, moId, nextId(), mo.getDisplayName());
		jonny.requestFriend(request);
		assertEquals("Mo", jonny.getOutgoingRequests().getOutgoingRequestsList().get(0).getDisplayName());
	}
	
	@Test
	public void testAcceptingFriendRequest()
	{
		Person jonny = new Person("cooldude5", "Jonny", "jonnyspw", nextId());
		Person mo = new Person("blackpanda7", "Mo", "pw", nextId());
		long jonnyId = jonny.id;
		long moId = mo.id;
		IncomingRequestsList incomingRequestsList = new IncomingRequestsList();
		PendingRequest request = new PendingRequest(jonnyId, moId, nextId(), mo.getDisplayName());
		incomingRequestsList.addIncomingRequest(request);
		mo.setIncomingRequests(incomingRequestsList);
		mo.acceptRequest(request);
		assertTrue(mo.getIncomingRequests().getIncomingRequestsList().isEmpty());
	}
	
	@Test
	public void testRejectingAFriend()
	{
		Person jonny = new Person("cooldude5", "Jonny", "jonnyspw", nextId());
		Person mo = new Person("blackpanda7", "Mo", "pw", nextId());
		long jonnyId = jonny.id;
		long moId = mo.id;
		IncomingRequestsList incomingRequestsList = new IncomingRequestsList();
		PendingRequest request = new PendingRequest(jonnyId, moId, nextId(), mo.getDisplayName());
		incomingRequestsList.addIncomingRequest(request);
		mo.setIncomingRequests(incomingRequestsList);
		mo.rejectRequest(request);
		assertTrue(mo.getIncomingRequests().getIncomingRequestsList().isEmpty());
	}
	
	@Test
	public void testUnfriending()
	{
		Person jonny = new Person("cooldude5", "Jonny", "jonnyspw", nextId());
		Friend mo = new Friend("Mo", nextId());
		jonny.getFriendList().addFriend(mo);
		jonny.removeFriend(mo);
		assertTrue(jonny.getFriendList().getListOfFriends().isEmpty());
	}
}
