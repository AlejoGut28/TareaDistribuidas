package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	private static final String SERVER_IP = "192.168.1.84";
	private static final int SERVER_PORT = 9090;
	private static final boolean AutoFlushWriter = true;

	public static void main(String[] args) throws IOException {

		Socket client1 = new Socket(SERVER_IP, SERVER_PORT);
		
		ServerConnection serverConn = new ServerConnection(client1);

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(client1.getOutputStream(), AutoFlushWriter);
		
		new Thread(serverConn).start();

		while (true) {
			System.out.print(">");
			String command = keyboard.readLine();

			if (command.equals("quit"))
				break;
			out.println(command);
			

		}
		client1.close();
		System.exit(0);
	}
}
