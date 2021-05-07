import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class HTTPEcho {
    public static void main( String[] args) throws  IOException {
        ServerSocket serversocket = new  ServerSocket(Integer.parseInt(args [0]));
        while(true) {
        	
        	Socket socket = serversocket.accept();
        	byte[] fromClient = new byte[5000];
        	socket.setSoTimeout(5000);
        	int length;
        	String end = "\r\n";
        	String html = "HTTP/1.1 200 OK"  + end + end; 
        	String toString;
        	StringBuilder sb = new StringBuilder();
        	sb.append(html);
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
        	
        	
        			    
        				
          
        	socket.getOutputStream().write(sb.toString().getBytes(StandardCharsets.UTF_8));
            socket.close();
        	
        }
        
        
    }
}


