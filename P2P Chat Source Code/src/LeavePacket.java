import java.io.Serializable;

public class LeavePacket implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5855985671956910531L;
	public String username;

	public LeavePacket(String user) {
		this.username = user;
	}
}