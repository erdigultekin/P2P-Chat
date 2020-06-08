import java.io.Serializable;

public class LoginPacket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3587273909632332119L;
	public String username;
	public String password;
	public boolean online = false;
	public int port;


	public LoginPacket(String user, String pass) {
		this.username = user;
		this.password = pass;
	}
}