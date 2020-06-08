
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class PeerGUI {
	public static JFrame frame;
	public JTextField usernameField;
	public JTextField passwordField;
	public JTextField usernameLoginField;
	public JTextField passwordLoginField;
	public static JTextField userSearchField;
	public static JTextField peerIPField;
	public static JTextField peerPortField;
	public JTextField chatTextToSend;
	public static JTextArea chatArea;
	
	public static InetAddress IP;
	
	public static String myName;
	public static String myIP;
	public static String myPort;
	
	public static String peerName;
	public static String peerIP;
	public static String peerPort;
	
	public static boolean isAvailable = true;
	public static boolean isOnline=false;
	public static String incomingMessage;
	public static JLabel statusLabel;
	
	public static PeerThread p;
	public static JButton connectButton;
	public static JButton registerButton;
	public static JButton loginButton;
	public static JButton sendButton;
	public static JButton searchButton;
	public static JButton logoutButton;
	
	public static JLabel bannerLabel;
	public Date dNow;
	public static FileWriter chatLog;
	public static String newLine = System.getProperty("line.separator");
    
	
	public PeerGUI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		bannerLabel = new JLabel("P2P Chat Application by Erdi Gultekin");
		bannerLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		bannerLabel.setBounds(287, 11, 253, 14);
		frame.getContentPane().add(bannerLabel);
		
		JLabel lblPleaseRegisterWith = new JLabel("New Peer Registration");
		lblPleaseRegisterWith.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPleaseRegisterWith.setBounds(38, 25, 140, 14);
		frame.getContentPane().add(lblPleaseRegisterWith);
		
		usernameField = new JTextField();
		usernameField.setBounds(92, 51, 86, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(92, 78, 86, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(92, 109, 89, 23);
		frame.getContentPane().add(registerButton);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(20, 54, 62, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 81, 62, 14);
		frame.getContentPane().add(lblPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 143, 197, 14);
		frame.getContentPane().add(separator);
		
		JLabel lblUserLogin = new JLabel("Peer Login");
		lblUserLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserLogin.setBounds(71, 157, 62, 14);
		frame.getContentPane().add(lblUserLogin);
		
		usernameLoginField = new JTextField();
		usernameLoginField.setBounds(92, 182, 86, 20);
		frame.getContentPane().add(usernameLoginField);
		usernameLoginField.setColumns(10);
		
		passwordLoginField = new JTextField();
		passwordLoginField.setBounds(92, 213, 86, 20);
		frame.getContentPane().add(passwordLoginField);
		passwordLoginField.setColumns(10);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(89, 244, 89, 23);
		frame.getContentPane().add(loginButton);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setBounds(20, 185, 62, 14);
		frame.getContentPane().add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(20, 216, 62, 14);
		frame.getContentPane().add(lblPassword_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 278, 197, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblSearchOtherPeers = new JLabel("Search Other Peers");
		lblSearchOtherPeers.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSearchOtherPeers.setBounds(38, 291, 120, 14);
		frame.getContentPane().add(lblSearchOtherPeers);
		
		userSearchField = new JTextField();
		userSearchField.setBounds(92, 316, 86, 20);
		frame.getContentPane().add(userSearchField);
		userSearchField.setColumns(10);
		
		JLabel lblUsername_2 = new JLabel("Username");
		lblUsername_2.setBounds(20, 319, 62, 14);
		frame.getContentPane().add(lblUsername_2);
		
		searchButton = new JButton("Search");
		searchButton.setBounds(89, 347, 89, 23);
		searchButton.setEnabled(false);
		frame.getContentPane().add(searchButton);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 377, 197, 7);
		frame.getContentPane().add(separator_2);
		
		peerIPField = new JTextField();
		peerIPField.setBounds(92, 400, 86, 20);
		frame.getContentPane().add(peerIPField);
		peerIPField.setColumns(10);
		
		JLabel lblConnectToA = new JLabel("Connect to a Peer");
		lblConnectToA.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConnectToA.setBounds(50, 383, 108, 14);
		frame.getContentPane().add(lblConnectToA);
		
		JLabel lblPeerName = new JLabel("Peer IP");
		lblPeerName.setBounds(20, 400, 74, 14);
		frame.getContentPane().add(lblPeerName);
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(89, 450, 89, 23);
		connectButton.setEnabled(false);
		frame.getContentPane().add(connectButton);
		
		statusLabel = new JLabel("Status : Register or login to start a conversation");
		statusLabel.setForeground(Color.RED);
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		statusLabel.setBounds(20, 473, 738, 14);
		frame.getContentPane().add(statusLabel);
		
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setBounds(226, 54, 520, 375);
		JScrollPane scroll = new JScrollPane (chatArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(226, 54, 540, 375);
		frame.getContentPane().add(scroll);
		
		chatTextToSend = new JTextField();
		chatTextToSend.setBounds(226, 440, 408, 22);
		frame.getContentPane().add(chatTextToSend);
		chatTextToSend.setColumns(10);
		
		sendButton = new JButton("Send");
		sendButton.setBounds(642, 439, 119, 23);
		sendButton.setEnabled(false);
		frame.getContentPane().add(sendButton);
		
		 logoutButton = new JButton("Leave");
		logoutButton.setBounds(659, 21, 89, 23);
		logoutButton.setEnabled(false);
		frame.getContentPane().add(logoutButton);
		
		peerPortField = new JTextField();
		peerPortField.setBounds(92, 425, 86, 20);
		frame.getContentPane().add(peerPortField);
		peerPortField.setColumns(10);
		
		JLabel lblPeerPort = new JLabel("Peer Port");
		lblPeerPort.setBounds(20, 425, 62, 14);
		frame.getContentPane().add(lblPeerPort);
		
		
		
		
		
		registerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				Packet registerP = new Packet();
				registerP.registerPacket = true;
				registerP.rg = new RegisterPacket(usernameField.getText(),passwordField.getText(), IP.getHostAddress());

				try {
					registerP = Peer.send(registerP);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				if(registerP.rg.registered == true) {
					statusLabel.setText("Status: Registration is successful. Now you can login.");
					usernameField.setEditable(false);
					passwordField.setEditable(false);
					
					registerButton.setEnabled(false);
				}else{
					statusLabel.setText("Status: Registration is not successful. Try a different name.");
					registerButton.setEnabled(true);
				}
			}
		});
		
		loginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Packet loginP = new Packet();
				loginP.loginPacket = true;
				loginP.lg = new LoginPacket(usernameLoginField.getText(),passwordLoginField.getText());
				

				try {
					loginP = Peer.send(loginP);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				if(loginP.lg.online == true) {
					myName = loginP.lg.username;
					myIP = IP.getHostAddress()+"";
					myPort = loginP.lg.port + "";
					isOnline=true;
					isAvailable = true;
					statusLabel.setText("Status: Login is successful. Now you can search a user or connect to a peer.");
					p= new PeerThread();
					p.start();
					
					try {
						chatLog = new FileWriter(myName+"'s Chatlog.txt");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					registerButton.setEnabled(false);
					loginButton.setEnabled(false);
					searchButton.setEnabled(true);
					connectButton.setEnabled(true);
					logoutButton.setEnabled(true);
					usernameLoginField.setEditable(false);
					passwordLoginField.setEditable(false);
					userSearchField.setEditable(true);
					peerIPField.setEditable(true);
					peerPortField.setEditable(true);
				}else{
					statusLabel.setText("Status: Login is unsuccessful. Try again.");
				}
			}
		});	
		
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				Packet searchP = new Packet();
				searchP.searchPacket = true;
				searchP.sp = new SearchPacket(userSearchField.getText());
				
				try {
					searchP = Peer.send(searchP);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				if(searchP.sp.isFound == true) {
					
					if(searchP.sp.isPeerOnline) {
						statusLabel.setText("Status: "+searchP.sp.name+" is a registered peer and online. IP: " +searchP.sp.ip+ " Port: " +searchP.sp.port);
					}else{
						statusLabel.setText("Status: "+searchP.sp.name+" is a registered peer and offline.");
					}
					
					registerButton.setEnabled(false);
					loginButton.setEnabled(false);
					searchButton.setEnabled(true);
					connectButton.setEnabled(true);
				}else{
					statusLabel.setText("Status: User is not found. Try again.");
				}
			}
		});	
		
		logoutButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Packet logoutP = new Packet();
				logoutP.leavePacket = true;
				logoutP.lv = new LeavePacket(myName);
				
				try {
					logoutP = Peer.send(logoutP);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(peerName.isEmpty()) {
					//do nothing
				}else{
					ChatPacket messageP = new ChatPacket("");
					messageP.isOffline = true;
					messageP.setSender(myName, myIP, myPort);

						System.out.println("message ready");
						try {
						messageP = Peer.sendToPeer(messageP, peerIP, peerPort);
						System.out.println("message sent");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
				}
				
				
				
				statusLabel.setText("Status: You have logged out. You can register a new username or login with a existing one." );
					isAvailable = true;
					isOnline=false;
					registerButton.setEnabled(true);
					loginButton.setEnabled(true);
					searchButton.setEnabled(false);
					connectButton.setEnabled(false);
					sendButton.setEnabled(false);
					logoutButton.setEnabled(false);
					usernameLoginField.setEditable(true);
					passwordLoginField.setEditable(true);
					usernameField.setEditable(true);
					passwordField.setEditable(true);
					try {
						chatLog.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});	
		
		
		connectButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			ChatPacket connectP = new ChatPacket("");
				connectP.isChatRequest = true;
				connectP.setSender(myName, myIP, myPort);
				peerPort = peerPortField.getText();
				peerIP = peerIPField.getText();
				peerName = "";
				connectP.setReceiver(peerName, peerIP, peerPort);
				
				try {
					
					connectP = Peer.sendToPeer(connectP, peerIP, peerPort);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					statusLabel.setText("Status: Wrong peer IP/Port. Please check IP/Port and try again." );
				}
				
			if(connectP.isAccepted){
					peerName = connectP.receiverName;
					peerIP = connectP.receiverIP;
					peerPort = connectP.receiverPort;
					statusLabel.setText("Status: "+peerName+" accepted your chat request" );
					isAvailable = false;
					registerButton.setEnabled(false);
					loginButton.setEnabled(false);
					searchButton.setEnabled(false);
					
					sendButton.setEnabled(true);
					logoutButton.setEnabled(true);
					usernameLoginField.setEditable(false);
					passwordLoginField.setEditable(false);
					
					connectButton.setEnabled(false);
					peerIPField.setEditable(false);
					peerPortField.setEditable(false);
					usernameField.setEditable(false);
					passwordField.setEditable(false);
					userSearchField.setEditable(false);
					searchButton.setEnabled(false);
					logoutButton.setEnabled(true);
					
				}else{
					if(connectP.isBusy){
						statusLabel.setText("Status: Peer is busy" );
					}else{
						statusLabel.setText("Peer rejected your chat request" );
						
					}
				}
				
				
			}
		});	
		
		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			ChatPacket messageP = new ChatPacket(chatTextToSend.getText());
			messageP.isMessage = true;
			messageP.setSender(myName, myIP, myPort);

				System.out.println("message ready");
				try {
				messageP = Peer.sendToPeer(messageP, peerIP, peerPort);
				System.out.println("message sent");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				if(messageP.isDelivered) {
				peerName = messageP.receiverName;
				peerIP = messageP.receiverIP;
				peerPort = messageP.receiverPort;
				SimpleDateFormat ft = new SimpleDateFormat ("HH:mm");
				dNow = new Date( );
				chatArea.append(ft.format(dNow)+ " "+ myName+": "+messageP.message +"\n");
				
				try {
					chatLog.write(ft.format(dNow)+ " "+ myName+": "+messageP.message + newLine);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
				
				chatTextToSend.setText("");
				}else{
					chatArea.append("Message is not delivered: "+messageP.message +"\n");
				}
				
			}
		});	
		
		
		
		
		frame.setVisible(true);
	}
		
		
	
	
	
    public static void main(String[] args) throws IOException {
    	PeerGUI pi = new PeerGUI();
    	IP=InetAddress.getLocalHost();
    	//if user closes the window, then send leave packet
    	Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    	    public void run() {
    	    	
    	    	Packet logoutP = new Packet();
				logoutP.leavePacket = true;
				logoutP.lv = new LeavePacket(myName);
				
				try {
					logoutP = Peer.send(logoutP);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(peerName.isEmpty()) {
					//do nothing
				}else{
					ChatPacket messageP = new ChatPacket("");
					messageP.isOffline = true;
					messageP.setSender(myName, myIP, myPort);

						System.out.println("message ready");
						try {
						messageP = Peer.sendToPeer(messageP, peerIP, peerPort);
						System.out.println("message sent");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
				}
				
					try {
						chatLog.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	    	
    	    	
    	    }
    	}));
    	
       
    }
    
    public static boolean askChatRequest(String user) {
   	 JDialog.setDefaultLookAndFeelDecorated(true);
	    int response = JOptionPane.showConfirmDialog(null, "Do you want to chat with "+ user +"?", "New Chat Request",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (response == JOptionPane.NO_OPTION) {
	      return false;
	    } else if (response == JOptionPane.YES_OPTION) {
	      return true;
	    } else {
	      return false;
	    }
    }
}

