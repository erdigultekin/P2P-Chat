import java.io.Serializable;

public class ChatPacket implements Serializable{


	private static final long serialVersionUID = 1163912835017501635L;
	public boolean isChatRequest = false;
	public boolean isMessage = false;
	public boolean isAccepted = false;
	public boolean isBusy = false;
	public boolean isDelivered = false;
	public boolean isOffline = false;
	public String message;
	public String senderName;
	public String receiverName;
	public String senderIP;
	public String receiverIP;
	public String senderPort;
	public String receiverPort;
	
	public ChatPacket(String m) {
		this.message = m;
	}
	
	public void setSender(String name, String ip, String port){
		this.senderIP = ip;
		this.senderName = name;
		this.senderPort = port;
	}
	
	public void setReceiver(String name, String ip, String port) {
		this.receiverIP = ip;
		this.receiverName = name;
		this.receiverPort = port;
	}

	
}