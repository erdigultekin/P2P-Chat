import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Peer {

		
		public ServerSocket peerServer = null;
		public Socket socket = null;
		public ObjectInputStream in = null;
		public ObjectOutputStream out = null;

	public static Packet send(Packet packet) throws IOException{
		String ip = "localhost";
		int port = 5555;
		Socket socket = null;

		try {
			//connect
			socket = new Socket(ip, port);

			//initialize streams
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			out.writeObject(packet);
			packet = (Packet) in.readObject();
			in.close();
			out.close();

		}
		catch (IOException e){
			e.printStackTrace();
			if (!socket.isClosed()){socket.close();}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return packet;
	}

		public static ChatPacket sendToPeer(ChatPacket packet, String ip, String p) throws IOException{
			
			int port = Integer.parseInt(p);
			
			Socket socket = null;
	
			try {
				//connect
				socket = new Socket(ip, port);
				
	
				//initialize streams
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				out.writeObject(packet);
				packet = (ChatPacket) in.readObject();
				in.close();
				out.close();
	
			}
			catch (IOException e){
				e.printStackTrace();
				if (!socket.isClosed()){socket.close();}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return packet;
		}
		
public static void sendMessage(ChatPacket packet, String ip, String p) throws IOException{
			
			int port = Integer.parseInt(p);
			
			Socket socket = null;
	
			try {
				//connect
				socket = new Socket(ip, port);
	
				//initialize streams
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(packet);
				out.close();
	
			}
			catch (IOException e){
				e.printStackTrace();
				if (!socket.isClosed()){socket.close();}
			}
	
		}

}