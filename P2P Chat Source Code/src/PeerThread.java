import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PeerThread extends Thread{

		
		public ServerSocket peerServer ;
		public Socket socket = null;
		public ObjectInputStream in = null;
		public ObjectOutputStream out = null;
		int port = Integer.parseInt(PeerGUI.myPort);
		
		 public Date dNow;

		public void run(){
			
			try {
				peerServer= new ServerSocket(port);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while(true) {
			
			try{					
					
					
					socket = peerServer.accept();
					in = new ObjectInputStream(socket.getInputStream());
					out = new ObjectOutputStream(socket.getOutputStream());
					
					ChatPacket packet = (ChatPacket) in.readObject();
					
				if(packet.isChatRequest) {
					if(PeerGUI.isAvailable){
						packet.isAccepted = PeerGUI.askChatRequest(packet.senderName);
						
						if(packet.isAccepted){
							packet.setReceiver(PeerGUI.myName, PeerGUI.myIP, PeerGUI.myPort);
							
							PeerGUI.peerIP = packet.senderIP;
							PeerGUI.peerName = packet.senderName;
							PeerGUI.peerPort = packet.senderPort;
							
							PeerGUI.connectButton.setEnabled(false);
							PeerGUI.statusLabel.setText("Status: Peer "+PeerGUI.peerName+ " is connected");
							PeerGUI.peerIPField.setEditable(false);
							PeerGUI.peerPortField.setEditable(false);
							PeerGUI.isAvailable = false;
							PeerGUI.registerButton.setEnabled(false);
							PeerGUI.loginButton.setEnabled(false);
							PeerGUI.sendButton.setEnabled(true);
							PeerGUI.searchButton.setEnabled(false);
							PeerGUI.userSearchField.setEditable(false);
							PeerGUI.logoutButton.setEnabled(true);
						}
						
							

					}else{
						packet.isBusy = true;
					}
					
				}else if(packet.isMessage){
					System.out.println("message received");
					SimpleDateFormat ft = new SimpleDateFormat ("HH:mm");
					dNow = new Date( );
					PeerGUI.chatArea.append(ft.format(dNow)+ " " +PeerGUI.peerName+": "+packet.message +"\n");
					
					try {
						PeerGUI.chatLog.write(ft.format(dNow)+ " " +PeerGUI.peerName+": "+packet.message + PeerGUI.newLine);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					packet.isDelivered = true;
					
					packet.setReceiver(PeerGUI.myName, PeerGUI.myIP, PeerGUI.myPort);
					PeerGUI.peerIP = packet.senderIP;
					PeerGUI.peerName = packet.senderName;
					PeerGUI.peerPort = packet.senderPort;

				}else if(packet.isOffline){
					PeerGUI.statusLabel.setText("Status: Peer "+PeerGUI.peerName+ " is offline");
					PeerGUI.peerIP = "";
					PeerGUI.peerName = "";
					PeerGUI.peerPort = "";
					PeerGUI.connectButton.setEnabled(true);
					PeerGUI.peerIPField.setEditable(true);
					PeerGUI.peerPortField.setEditable(true);
					PeerGUI.isAvailable = true;
					PeerGUI.sendButton.setEnabled(false);
					PeerGUI.searchButton.setEnabled(true);
					PeerGUI.userSearchField.setEditable(true);
				}
				
				out.writeObject(packet);
				
			}catch (IOException e) {
				System.out.println("Peer disconnected!");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}	
			
		}

}