package domainModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPerson
{
	
	@Test
	public void testAddRequestAndAccept()
	{
		Person personOne = new Person("myUsername", "Me");
		Person personTwo = new Person("hisUsername", "Him");
		personOne.requestFriend(personTwo);
		assertEquals("Me", personTwo.getIncomingRequests().getIncomingRequestsList().get(0).getDisplayName());
		assertEquals("Him", personOne.getOutgoingRequests().getOutgoingRequestsList().get(0).getDisplayName());
		
		personTwo.acceptRequest(personOne);
		assertEquals("Me", personTwo.getFriendList().getListOfFriends().get(0).getDisplayName());
		assertEquals("Him", personOne.getFriendList().getListOfFriends().get(0).getDisplayName());
	}
	
//	@Test
//	public void testRemoveFriend()
//	{
//		Person personOne = new Person("myUsername", "Me");
//		Person personTwo = new Person("hisUsername", "Him");
//		personOne.requestFriend(personTwo);
//		personTwo.acceptRequest(personOne);
//		
//		personOne.removeFriend(personTwo);
//	}
}
