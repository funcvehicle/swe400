import commands.*;
import domainModel.Person;


public class mainTester
{
	public static void main(String[] args)
	{	
//		CommandToCreateUser createKarl = new CommandToCreateUser("Karl", "karlspassword", "Karly");
//		createKarl.execute();
//		Person karl = createKarl.getResult();
		
//		CommandToCreateUser createTed = new CommandToCreateUser("Ted", "tedspassword", "teddy");
//		createTed.execute();
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
		
//		CommandToMakeFriendRequest requestKarl = new CommandToMakeFriendRequest(3, "Karl");
//		requestKarl.execute();
//		
//		persist.execute();
		
		CommandToAcceptFriendRequest acceptTed = new CommandToAcceptFriendRequest(2, "Ted");
		acceptTed.execute();
	}
}
