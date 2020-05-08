package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
	private static final boolean AutoFlushWriter = true;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<ClientHandler> clients;
	
	public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
		this.client = clientSocket;
        this.clients = clients;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream() , AutoFlushWriter);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String request = in.readLine();
				if (request.contains("name")) {
					out.print(Server.getRandonName());
				} else if(request.startsWith("say")) {
					int firstSpace = request.indexOf(" ");
					 if(firstSpace != -1) { 
					   outToAll(request.substring(firstSpace+1));
					}
				} else {
					out.print("Continua con el mensaje :) : ");
				}
			}

		}catch (IOException e) {
			  System.err.println("IO exception in client handler");
			  System.err.println(e.getStackTrace());
		}  finally {
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void outToAll(String msg) {
		// TODO Auto-generated method stub
		for (ClientHandler aClient : clients) {
			aClient.out.println(msg);
		}
	}

}
