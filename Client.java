import java.net.Socket;
import java.util.Date;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Client {
	Socket s = null;
	ObjectInputStream objis = null;

	public Client(String address, int port) {
		try {
			s = new Socket(address, port);
			System.out.println("Connected to " + address + ":" + port + " at " + new Date());

			objis = new ObjectInputStream(s.getInputStream());
			AttendExam attendExam = (AttendExam) objis.readObject();
			attendExam.serve();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] agrs) {
		if (agrs.length != 2) {
			System.out.println("Please enter 2 arguments !");
			System.out.println("First as Server address and Second Port number");
		} else {
			Client c = new Client(agrs[0], Integer.parseInt(agrs[1]));
		}
	}
}