package player;

/*
 * (Temporary) player package & class
 */

public class Player {
	
	private String ID;
	private String username;
	//setting this public for ease of use
	public static int coins;
	
	public Player(String name){
		//calculate arbitrary userID
		ID = "000001";
		username = name;
		coins = 500;
	}
	
	//rename user
	public void setUsername(String newname){
		username = newname;
	}

}
