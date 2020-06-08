import java.util.Random;

public class PeerDB {
	
	public static int numberOfPeers = 50;
	public static String[] usernames = new String[numberOfPeers];
	public static String[] passwords = new String[numberOfPeers];
	public static String[] ips = new String[numberOfPeers];
	public static int[] ports = new int[numberOfPeers];
	public static boolean[] onlineStatus = new boolean[numberOfPeers];
	public static int registerIndex= 0;
	

	public static void register(RegisterPacket packet){
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(5000 - 4000) + 4000;
		usernames[registerIndex] = packet.username;
		passwords[registerIndex] = packet.password;
		ips[registerIndex]= packet.ip;
		onlineStatus[registerIndex] = false;
		ports[registerIndex] = randomNumber;
		registerIndex++;
	}
	
	public static boolean isRegistered(String user){
		for(int i=0; i<registerIndex; i++){
			if(usernames[i].equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isAuthorized(LoginPacket lg){
		for(int i=0; i<registerIndex; i++) {
			if(usernames[i].equals(lg.username) && passwords[i].equals(lg.password)){
				return true;
			}
		}
		return false;
	}
	
	public static void makeOnline(String user){
		for(int i=0; i<registerIndex; i++) {
			if(usernames[i].equals(user)){
			onlineStatus[i] = true;
			}
		}
	}
	
	public static void makeOffline(String user){
		for(int i=0; i<registerIndex; i++) {
			if(usernames[i].equals(user)){
			onlineStatus[i] = false;
			}
		}
	}
	
	public static boolean isOnline(String user){
		for(int i=0; i<registerIndex; i++) {
			if(usernames[i].equals(user)){
				if(onlineStatus[i]){
					return true;
				}
			}
		}
		return false;
	}
	
	public static String getIP(String user){
		for(int i=0; i<registerIndex; i++) {
			if(usernames[i].equals(user)){
			return ips[i];
			}
		}
		return "";
	}
	
	public static int getPort(String user){
		for(int i=0; i<registerIndex; i++) {
			if(usernames[i].equals(user)){
			return ports[i];
			}
		}
		return 0;
	}
	
}