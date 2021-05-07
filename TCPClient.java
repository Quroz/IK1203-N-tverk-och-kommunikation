package tcpclient;


import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class TCPClient {
    
    public static String askServer(String hostname, int port, String ToServer) throws  IOException {
    	
    	
    	Socket socket = new Socket(hostname, port);
    	socket.setSoTimeout(5000);   //skapar en timer
    	
    	socket.getOutputStream().write(ToServer.getBytes(StandardCharsets.UTF_8));  
    	socket.getOutputStream().write('\n');
    	
    	byte[] fromServer = new byte[5000];   
    	String toString;
    	
    	//byte[] fromServer =  socket.getInputStream().readAllBytes();
    	StringBuilder sb = new StringBuilder();
    	int length;
    	boolean loop = true;
        //int length =  socket.getInputStream().read(fromServer);
    	while(loop){   
    		try {
    			
    			
    			    length =  socket.getInputStream().read(fromServer);  
    			    if(length != -1) {
    			    	 toString = new String(fromServer,0, length, StandardCharsets.UTF_8);
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
    	
   
    
    	socket.close();
    	return sb.toString();
    	
    	
    
    	
    	 }

    public static String askServer(String hostname, int port) throws  IOException {
    	
      	

      	Socket socket = new Socket(hostname, port);
    	socket.setSoTimeout(5000);
    	
    	
    	
    	byte[] fromServer = new byte[5000];
    	String toString;
    	
    	//byte[] fromServer =  socket.getInputStream().readAllBytes();
    	StringBuilder sb = new StringBuilder();
    	int length;
    	boolean loop = true;
        //int length =  socket.getInputStream().read(fromServer);
    	while(loop){   
    		try {
    			
    			
    			    length =  socket.getInputStream().read(fromServer);  
    			    if(length != -1) {
    			    	 toString = new String(fromServer,0, length, StandardCharsets.UTF_8);
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
    	
   
    
    	socket.close();
    	return sb.toString();
}
}


