package mappers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mapper.FriendMapper;
import mapper.MapperRegistry;
import org.junit.Test;
import domainModel.Friend;
import domainModel.FriendList;

public class testFriendMapper 
{
	private long userId = 0;
	private long friendId = 1;
	private MapperRegistry registry = MapperRegistry.getCurrent();
	private FriendMapper mapper = (FriendMapper) registry.getMapper(Friend.class);
	private Friend friend = mapper.create("bobbyj7", friendId, userId);
	
	@Test
	public void testCreatingAFriend()
	{
		assertEquals(userId, friend.getCurrentUserId());
		assertEquals(friendId, friend.getId());
		assertEquals("bobbyj7", friend.getDisplayName());
	}
	
	@Test
	public void testFindingFriends()
	{
		FriendList friendList = mapper.findFriends(userId);
		boolean found = false;
		for (Friend f : friendList.getListOfFriends())
		{
			if (friend.getRelationshipId() == f.getRelationshipId())
			{
				found = true;	
			}
		}
	}
	
	@Test
	public void testDeletingAFriend()
	{
		mapper.delete(friend);
		FriendList friendList = mapper.findFriends(userId);
		boolean deleted = true;
		for (Friend f : friendList.getListOfFriends())
		{
			if (friend.getRelationshipId() == f.getRelationshipId())
			{
				deleted = false;	
			}
		}
		assertTrue(deleted);
	}
}
