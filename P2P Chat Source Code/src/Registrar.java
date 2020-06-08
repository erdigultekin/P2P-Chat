
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Registrar {
	
	public static volatile RThread[] peersConnected;
	
	public Registrar(){

		int maxNumberofPeers = 20;
		int port = 5555;
		ServerSocket server = null;
		Socket socket = null;
		
		peersConnected = new RThread[maxNumberofPeers];


		try {
			server = new ServerSocket(port);
			System.out.println("listening on port: " + port);
		} catch (IOException e) {// TODO Auto-generated catch block
			e.printStackTrace();

		}

		while (true) {
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
				if (!server.isClosed()){try {
					server.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
				if (!socket.isClosed()){try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
			}

			System.out.println("Peer connected!");

			for (int c = 0; c < peersConnected.length; c++){
				if (peersConnected[c] == null){
					(peersConnected[c] = new RThread(socket)).start();
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Registrar registrar = new Registrar();

	}
}