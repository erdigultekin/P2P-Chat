import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class RThread extends Thread{

	private Socket socket = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;

	public RThread(Socket socket){
		this.socket = socket;
	}
	//Registrar thread starts here
	public void run(){
		try {
			//Inputs and outputs
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());

			System.out.println("Reading the incoming packet");
			Packet packet = (Packet) in.readObject();
			
			//New peer registration
			if(packet.registerPacket){
				if(PeerDB.isRegistered(packet.rg.username)){
					packet.rg.registered = false;
					System.out.println("Peer already registered");
				}else{
					PeerDB.register(packet.rg);
					packet.rg.registered = true;
					System.out.println("Peer registered successfully");
					
				}
			//Peer logins	
			}else if(packet.loginPacket){
				if(PeerDB.isAuthorized(packet.lg)) {
					PeerDB.makeOnline(packet.lg.username);
					System.out.println("Peer is online");
					packet.lg.port = PeerDB.getPort(packet.lg.username);
					packet.lg.online = true;
				}else{
					packet.lg.online = false;
					System.out.println("Wrong username or password");
				}
				
			//Peer leaves	
			}else if(packet.leavePacket){
				PeerDB.makeOffline(packet.lv.username);
				System.out.println("Peer logged out");
				
			//Peer is looking for another peer
			}else if(packet.searchPacket){
				if(PeerDB.isRegistered(packet.sp.name)){
					packet.sp.isFound = true;
					packet.sp.isPeerOnline = PeerDB.isOnline(packet.sp.name);
					packet.sp.ip = PeerDB.getIP(packet.sp.name);
					packet.sp.port = PeerDB.getPort(packet.sp.name);
				}
				
			//Unknown packets	
			}else{
				
			}
			
	        out.writeObject(packet);
	       
	        System.out.println("Object sent to peer");
			

			for(int i=0; i<Registrar.peersConnected.length;i++){
				if(Registrar.peersConnected[i]!=null){
					if(Registrar.peersConnected[i].equals(this)){
						Registrar.peersConnected[i] = null;
					}
				}
			}

		} catch (IOException e) {
			System.out.println("Peer disconnected!");
			for(int i=0; i<Registrar.peersConnected.length;i++){
				if(Registrar.peersConnected[i]!=null){
					if(Registrar.peersConnected[i].equals(this)){
						Registrar.peersConnected[i] = null;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}