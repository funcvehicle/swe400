import mapper.MapperRegistry;
import commands.*;
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
		
//		CommandToSelectUser selectKarl = new CommandToSelectUser("Karl", "karlspassword");
//		selectKarl.execute();
//		
//		CommandToMakeFriendRequest requestTed = new CommandToMakeFriendRequest(0, "");
	}
}
