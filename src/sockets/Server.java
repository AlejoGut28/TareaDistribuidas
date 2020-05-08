package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private static final int port = 9090;
	private static final boolean AutoFlushWriter = true;
	private static final int nThreads = 4;
	private static String[] names = { "Willy", "Felix", "Randy", "Henry" };
	private static String[] ajds = { "el fuerte", "valeroso", "el arrebatado", "el temible" };
	
	private static ArrayList<ClientHandler> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(nThreads);

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(port);
              
		while(true) { 
				System.out.println("[SERVER] Waiting for client connection..");
				Socket client = server.accept();
				System.out.println("[SERVER] Connected to client!");
				ClientHandler clientThread = new ClientHandler(client, clients);
				clients.add(clientThread);
				
				pool.execute(clientThread);
           }
	}

	public static String getRandonName() {
		String name = names[(int) (Math.random() * names.length)];
		String adj = ajds[(int) (Math.random() * ajds.length)];
		return name + " " + adj;
	}
}
