import java.net.Socket;
import java.util.Date;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Client {
	Socket s = null;
	DataInputStream in = null, reply = null;
	DataOutputStream out = null;
	DataInputStream rr = null;

	public Client(String address, int port) {
		try {
			s = new Socket(address, port);
			System.out.println("Connected to " + address + ":" + port + " at " + new Date());

//			in = new DataInputStream(System.in);
//			out = new DataOutputStream(s.getOutputStream());
//			rr = new DataInputStream(s.getInputStream());
//			// reply = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

//		String str = "";
//		while (!str.equals("submit")) {
//			try {
//				str = in.readLine();
//				out.writeUTF(str);
//				System.out.println(rr.readUTF());
//				// System.out.println(reply.readUTF());
//			} catch (IOException io) {
//				System.out.println(io);
//			}
//		}

		try {
			s.close();
			in.close();
			// reply.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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