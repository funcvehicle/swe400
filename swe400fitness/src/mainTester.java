import domainLogic.*;
import domainModel.Person;


public class mainTester
{
	public static void main(String[] args)
	{	
		CommandToCreateUser createKarl = new CommandToCreateUser("Karl", "karlspassword", "Karly");
		createKarl.execute();
		Person karl = createKarl.getResult();
		
		CommandToCreateUser createTed = new CommandToCreateUser("Ted", "tedspassword", "teddy");
		createTed.execute();
		Person ted = createTed.getResult();
		
		CommandToSelectUser selectKarl = new CommandToSelectUser("Karl", "karlspassword");
		selectKarl.execute();
		System.out.println("Selected: " + selectKarl.getResult().getDisplayName());
		
		CommandToModifyUser modifyTed = new CommandToModifyUser(ted.getId(), "teddo");
		modifyTed.execute();
		
		CommandToPersistChanges persist = new CommandToPersistChanges();
		persist.execute();
		
		CommandToSelectUser selectTed = new CommandToSelectUser("Ted", "tedspassword");
		selectTed.execute();
		System.out.println("Selected: " + selectTed.getResult().getDisplayName());
		
		CommandToMakeFriendRequest requestKarl = new CommandToMakeFriendRequest(ted.getId(), "Karl");
		requestKarl.execute();
		persist.execute();
		
		CommandToGetPendingIncomingFriendList karlsIncoming = new CommandToGetPendingIncomingFriendList(karl.getId());
		karlsIncoming.execute();
		System.out.println("Karl's incoming: " + karlsIncoming.getResult().get(0).getDisplayName());
		
		CommandToGetPendingOutgoingFriendList tedsOutgoing = new CommandToGetPendingOutgoingFriendList(ted.getId());
		tedsOutgoing.execute();
		System.out.println("Ted's outgoing: " + tedsOutgoing.getResult().get(0).getDisplayName());
		
//		CommandToRejectFriendRequest rejectTed = new CommandToRejectFriendRequest(karl.getId(), "Ted");
//		rejectTed.execute();
//		persist.execute();
		
		//persist.execute();
		
		CommandToAcceptFriendRequest acceptTed = new CommandToAcceptFriendRequest(karl.getId(), "Ted");
		acceptTed.execute();
		persist.execute();
		
		CommandToRetrieveFriendList tedsFriends = new CommandToRetrieveFriendList(ted.getId());
		tedsFriends.execute();
		System.out.println("Ted's friends: " + tedsFriends.getResult().get(0).getDisplayName());
		
		CommandToRetrieveFriendList karlsFriends = new CommandToRetrieveFriendList(karl.getId());
		karlsFriends.execute();
		System.out.println("Karl's friends: " + karlsFriends.getResult().get(0).getDisplayName());
		
		CommandToUnFriend unfriendTed = new CommandToUnFriend(karl.getId(), "Ted");
		unfriendTed.execute();
		persist.execute();
	}
}
