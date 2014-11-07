package domainModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unitOfWork.UnitOfWork;
/**
 * 
 * @author Emily Maust, Olivia Pompa
 *
 */
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
		personOne.requestFriend(personTwo);
		assertEquals(personTwo.id, personOne.getOutgoingRequests().getOutgoingRequestsList().get(0).id);
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
		personTwo.rejectRequest(personOne.asFriend(personOne.id));
		assertTrue(personTwo.getFriendList().getListOfFriends().isEmpty());
		assertTrue(personTwo.getIncomingRequests().getIncomingRequestsList().isEmpty());
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
	
	@Test
	public void testUpdateName()
	{
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		personOne.setDisplayName("you");
		assertEquals("you", personOne.getDisplayName());
	}
	
	@Test
	public void testCannotFriendSelf()
	{
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		personOne.requestFriend(personOne);
		assertTrue(personOne.getIncomingRequests().getIncomingRequestsList().isEmpty());
	}
	
	@Test
	public void cannotRequestTwice()
	{
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		Person personTwo = new Person("hisUsername", "Him", "pw", 1);
		personOne.requestFriend(personTwo);
		personOne.requestFriend(personTwo);
		assertEquals(1, personTwo.getIncomingRequests().getIncomingRequestsList().size());
	}
	
	@Test
	public void cannotRequestCurrentFriend()
	{
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		Person personTwo = new Person("hisUsername", "Him", "pw", 1);
		personOne.requestFriend(personTwo);
		personTwo.acceptRequest(personOne);
		personOne.requestFriend(personTwo);
		assertEquals(0, personTwo.getIncomingRequests().getIncomingRequestsList().size());
	}
	
	@Test
	public void cannotRequestARequester()
	{
		Person personOne = new Person("myUsername", "Me", "pw", 0);
		Person personTwo = new Person("hisUsername", "Him", "pw", 1);
		personOne.requestFriend(personTwo);
		personTwo.requestFriend(personOne);
		assertTrue(personOne.getIncomingRequests().getIncomingRequestsList().isEmpty());
	}
}
