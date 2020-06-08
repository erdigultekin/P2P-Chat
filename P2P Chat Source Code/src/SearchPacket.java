import java.io.Serializable;

public class SearchPacket implements Serializable{

	private static final long serialVersionUID = -6851536238626494420L;
	
	public String name;
	public boolean isFound = false;
	public boolean isPeerOnline = false;
	public String ip;
	public int port;


	public SearchPacket(String name){
		this.name = name;
	}
	
}