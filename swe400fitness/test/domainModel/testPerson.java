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
		
//		personTwo.acceptRequest(personOne);
//		personOne.getFriendsList();
	}
	
}
