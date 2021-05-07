import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import tcpclient.TCPClient;
public class HTTPAsk {
	public static void main( String[] args) throws IOException {
		ServerSocket serversocket = new  ServerSocket(Integer.parseInt(args [0]));
		while(true) {
			Socket socket = serversocket.accept();
			byte[] fromClient = new byte[5000];
			socket.setSoTimeout(5000);
			int length;
			String end = "\r\n";
			String toServer = null;
			String port = null;
			String hostname = null;
			String HTTPOk = "HTTP/1.1 200 OK"  + end + end; 
			String BadRequest = "HTTP/1.1 400 Bad Request" + end + end; 
			String NotFound = "HTTP/1.1 404 Not Found" + end + end;
			String toString;
			StringBuilder sb = new StringBuilder();
			
			boolean loop = true;
			while(loop){   
				try {


					length =  socket.getInputStream().read(fromClient);  
					if(length != -1) {
						toString = new String(fromClient,0, length, StandardCharsets.UTF_8);
						sb.append(toString);
					}
					else {
						break;
					}

					
					
				}

				catch(IOException e) {
					break;
				}

			}

			String[] request = sb.toString().split("[ =&?/]");
			int ask = request.length -1;
			for(int i = 0; i < request.length; i++) {
				if(request[i].equals("ask")) {
					
					ask = i;
				}
				
			}
			boolean HTTPCheck = false;
			boolean portCheck = false;
			boolean hostCheck = false;
			
			boolean GETCheck = false;
			
				if(request[ask].equals("ask")) {
					for(int i = 0; i < request.length; i++) { 
						if(request[i].equals("hostname")) {
							hostname = request[i + 1];
							hostCheck = true;
						
						}
						
						else if(request[i].equals("port")) {
							port = request[i + 1];
							portCheck = true;
							
						}
						
						else if(request[i].equals("string")) {
							toServer = request[i + 1];
							
						}
						else if(request[i].equals("HTTP"))
							HTTPCheck = true;
						
						else if(request[i].equals("GET"))
							GETCheck = true;
					}
				}
				
				else {
					socket.getOutputStream().write(BadRequest.getBytes(StandardCharsets.UTF_8));
				}
				
				if(!HTTPCheck  || !portCheck  || !hostCheck || !GETCheck)
					socket.getOutputStream().write(BadRequest.getBytes(StandardCharsets.UTF_8));
			try {
				 if(toServer != null) {
					String serverOutput = TCPClient.askServer(hostname,Integer.parseInt(port),toServer);
					socket.getOutputStream().write((HTTPOk + serverOutput).getBytes(StandardCharsets.UTF_8));
					
				}
				
				
				 else {
					String serverOutput = TCPClient.askServer(hostname,Integer.parseInt(port));
					socket.getOutputStream().write((HTTPOk + serverOutput).getBytes(StandardCharsets.UTF_8));
			 }
			}
				 
           catch(IOException e) {
        	   socket.getOutputStream().write(NotFound.getBytes(StandardCharsets.UTF_8));
           }

				socket.close();

		}

		

	

	}
}

