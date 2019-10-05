import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Server {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket s = null;
		ObjectOutputStream objos = null;
		int port = 5000;

		try {
			server = new ServerSocket(port);
			System.out.println("Server started on " + InetAddress.getLocalHost().getHostAddress() + ":" + port + " at "
					+ new Date());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				System.out.println("Waiting for client to connect from " + new Date());
				s = server.accept();
				System.out.println("New client connected at " + new Date());
				
				objos = new ObjectOutputStream(s.getOutputStream());
				AttendExam attendExam = new AttendExam();
//				attendExam.chooseExamPaper();
				objos.writeObject(attendExam);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
//			ServeClient sc = new ServeClient(s);
		}
	}
}