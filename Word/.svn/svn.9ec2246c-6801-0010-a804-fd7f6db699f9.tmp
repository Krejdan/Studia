package util;

import java.io.*;
import java.net.Socket;

import server.Request;

public class Connection {

	private static Socket socket;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	public static Request request = new Request();
	
	public static void connect(String address, int port) throws IOException {
		socket = new Socket(address, port);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}
	
	public static void disconnect() throws IOException {
		socket.close();
	}
 	
	public static ObjectOutputStream getOutput() {
		return output;
	}
	public static ObjectInputStream getInput() {
		return input;
	}
	public static Request getRequest() {
		return request;
	}
	public static void setRequest(Request request) {
		Connection.request = request;
	}	
}
