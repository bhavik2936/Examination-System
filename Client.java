import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Client {
	private String ipAddress;
	private int port;
	private Socket socket = null;
	private ObjectInputStream objis = null;

	// one and only parameterized constructor
	public Client(String ipAddress, int port) {
		if (ipAddress == "localhost")
			this.ipAddress = "127.0.0.1";
		else
			this.ipAddress = ipAddress;
		this.port = port;
	}

	// responsible for connection to Server
	boolean connectServer() throws UnknownHostException, IOException {
		socket = new Socket(ipAddress, port);
		System.out.println("Connected to " + ipAddress + ":" + port + " at " + new Date());
		return true;
	}

	// responsible for fetching paper from server stream
	boolean fetchPaper() throws ClassNotFoundException, IOException {
		objis = new ObjectInputStream(socket.getInputStream());
		AttendExam attendExam = (AttendExam) objis.readObject();
		attendExam.serve();
		return true;
	}

	// sends request to server
	void requestServer() throws ClassNotFoundException, IOException {
		connectServer();
		fetchPaper();
	}

	public static void main(String[] agrs) {
		if (agrs.length != 2) {
			System.out.println("Please enter 2 arguments !");
			System.out.println("First as Server address and Second Port number");
			System.exit(0);
		}
		Client client = new Client(agrs[0], Integer.parseInt(agrs[1]));
		try {
			client.requestServer();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				client.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}