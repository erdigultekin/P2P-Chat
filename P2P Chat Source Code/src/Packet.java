import java.io.Serializable;

public class Packet implements Serializable{

	private static final long serialVersionUID = -6191071452147592498L;
	
	//Packet Types
	public boolean registerPacket = false;
	public boolean loginPacket = false;
	public boolean leavePacket = false;
	public boolean searchPacket = false;
	public boolean chatRequestPacket = false;
	public boolean chatRequestReplyPacket = false;
	public boolean chatMessagePacket = false;
	
	//Packet Contents
	public RegisterPacket rg;
	public LoginPacket lg;
	public LeavePacket lv;
	public SearchPacket sp;


	
}