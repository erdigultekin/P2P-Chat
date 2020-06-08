import java.io.Serializable;

public class RequestPacket implements Serializable{

	private static final long serialVersionUID = -8241775526933754354L;
	public String username;
	public String senderIP;
	public boolean isAccepted = false;
	public boolean isBusy = false;
	public String peerName;
	public int senderPort;

	public RequestPacket(String name, String ip, int p) {
		this.senderIP = ip;
		this.username = name;
		this.senderPort = p;
	}
}
