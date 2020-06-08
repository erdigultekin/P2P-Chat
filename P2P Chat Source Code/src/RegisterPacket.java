import java.io.Serializable;

public class RegisterPacket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4922475661520372929L;
	public String username;
	public String password;
	public String ip;
	public boolean onlineStatus;
	public boolean registered = false;

	public RegisterPacket(String user, String pass, String ip) {
		this.username = user;
		this.password = pass;
		this.ip = ip;
		this.onlineStatus = true;
	}
}
