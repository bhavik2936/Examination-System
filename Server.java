import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Server {
	private ServerSocket serverSocket = null;
	private Socket s = null;
	private ObjectOutputStream objos = null;
	private int port = 5000;

	// returns port
	String getPort() {
		return Integer.toString(port);
	}

	// responsible for starting the server
	public boolean startServer() throws UnknownHostException, IOException {
		serverSocket = new ServerSocket(port);
		System.out.println(
				"Server started on " + InetAddress.getLocalHost().getHostAddress() + ":" + port + " at " + new Date());
		return true;
	}

	// responsible for client connection
	private boolean acceptClient() throws IOException {
		System.out.println("Waiting for client to connect from " + new Date());
		s = serverSocket.accept();
		System.out.println("New client connected at " + new Date());
		return true;
	}

	// responsible for distributing the paper
	private boolean serveClient() throws IOException {
		System.out.println("Atteding to distribute paper at" + new Date());
		objos = new ObjectOutputStream(s.getOutputStream());
		AttendExam attendExam = new AttendExam();
		objos.writeObject(attendExam);
		return true;
	}

	// keeps the server live
	public void liveServer() throws IOException {
		while (true) {
			acceptClient();
			serveClient();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();

		try {
			if (args.length == 0)
				server.startServer();
			server.liveServer();
		} catch (BindException e) {
			System.out.println("Server starting falied !");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			args[0] = server.getPort();
			main(args);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}