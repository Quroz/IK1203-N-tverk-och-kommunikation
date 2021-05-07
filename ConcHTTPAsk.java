

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConcHTTPAsk {

	

	public static void main(String[] args) throws IOException {
		ServerSocket serversocket = new  ServerSocket(Integer.parseInt(args [0]));
		
		while(true) {
			
			Socket socket = serversocket.accept();
			new Thread (new MyRunnable(socket)).start();
			
			
		}
	}

}
