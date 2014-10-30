import commands.*;
import domainModel.Person;


public class mainTester
{
	public static void main(String[] args)
	{	
//		CommandToCreateUser createKarl = new CommandToCreateUser("Karl", "karlspassword", "Karly");
//		createKarl.execute();
//		PersonKarl = createKarl.getResult();
//		
//		CommandToCreateUser createTed = new CommandToCreateUser("Ted", "tedspassword", "teddy");
//		createTed.execute();
//		Person ted = createTed.getResult();
//		
//		CommandToSelectUser selectKarl = new CommandToSelectUser("Karl", "karlspassword");
//		selectKarl.execute();
//		System.out.println(selectKarl.getResult().getDisplayName());
//		
//		CommandToModifyUser modifyTed = new CommandToModifyUser(3, "teddo");
//		modifyTed.execute();
//		
		CommandToPersistChanges persist = new CommandToPersistChanges();
//		persist.execute();
//		
//		CommandToSelectUser selectTed = new CommandToSelectUser("Ted", "tedspassword");
//		selectTed.execute();
//		System.out.println(selectTed.getResult().getDisplayName());
//		
//		CommandToMakeFriendRequest requestKarl = new CommandToMakeFriendRequest(3, "Karl");
//		requestKarl.execute();
//		persist.execute();
//		
//		CommandToRejectFriendRequest rejectTed = new CommandToRejectFriendRequest(2, "Ted");
//		rejectTed.execute();
//		persist.execute();
//		
//		persist.execute();
//		
//		CommandToAcceptFriendRequest acceptTed = new CommandToAcceptFriendRequest(2, "Ted");
//		acceptTed.execute();
//		persist.execute();
//		
//		CommandToUnFriend unfriendTed = new CommandToUnFriend(2, "Ted");
//		unfriendTed.execute();
//		persist.execute();
		
		CommandToRetrieveFriendList tedsFriends = new CommandToRetrieveFriendList(3);
		tedsFriends.execute();
		System.out.println(tedsFriends.getResult().get(0).getDisplayName());
		
		CommandToRetrieveFriendList karlsFriends = new CommandToRetrieveFriendList(2);
		karlsFriends.execute();
		System.out.println(karlsFriends.getResult().get(0).getDisplayName());
	}
}
