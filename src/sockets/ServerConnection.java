package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable{
	private static final boolean AutoFlushWriter = true;
	private Socket server;
	private BufferedReader in;
	private PrintWriter out;
	
	
	public ServerConnection(Socket s) throws IOException {
		server = s;
		in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		out = new PrintWriter(server.getOutputStream() , AutoFlushWriter);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			try {
				 while(true) {
					  String serverResponse = in.readLine();
					  
					  if(serverResponse == null ) break;
					  
					  System.out.println("Server says: " + serverResponse);
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
	}

}
