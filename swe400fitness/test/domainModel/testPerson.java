package domainModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unitOfWork.UnitOfWork;

public class testPerson
{
	@Before
	public void init()
	{
		UnitOfWork.newCurrent();
	}
	
	@Test
	public void testRequestAFriend()
	{
		
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		Person personTwo = new Person("hisUsername", "Him", "pw", 1);
		PendingRequest request = new PendingRequest(personOne.getId(), personTwo.getId(), 0, "");
		personOne.requestFriend(request);
		assertEquals("Me", personTwo.getIncomingRequests().getIncomingRequestsList().get(0).getDisplayName());
		assertEquals("Him", personOne.getOutgoingRequests().getOutgoingRequestsList().get(0).getDisplayName());
	}
	
	@Test
	public void testAcceptingAFriend()
	{
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		Person personTwo = new Person("hisUsername", "Him", "pw", 1);
		personOne.requestFriend(personTwo);

		assertTrue(personTwo.acceptRequest(personOne));
		assertEquals("Me", personTwo.getFriendList().getListOfFriends().get(0).getDisplayName());
		assertEquals("Him", personOne.getFriendList().getListOfFriends().get(0).getDisplayName());
	}
	
	@Test
	public void testRejectARequest()
	{
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		Person personTwo = new Person("hisUsername", "Him", "pw", 1);
		personOne.requestFriend(personTwo);
		
		assertTrue(personTwo.removeIncomingRequest(personOne));
	}
	
	@Test
	public void testRemoveFriend()
	{
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		Person personTwo = new Person("hisUsername", "Him", "pw", 1);
		personOne.requestFriend(personTwo);
		personTwo.acceptRequest(personOne);
		
		assertTrue(personOne.removeFriend(personTwo));
	}
}
