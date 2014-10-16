package client;

import commands.*;

public class Client 
{
	public static void main(String[] args)
	{
		//AcceptFriendRequest acceptFriend = new AcceptFriendRequest(0, null);
		Command createUser = new CreateUserCommand("billgates", "billgates123", "bill");
		
		createUser.execute();
	}

}
