import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServeClient implements Runnable {
	DataInputStream in = null;
	DataOutputStream out = null;
	Socket s = null;
	Thread t = null;
	static int count = 1;

	public ServeClient(Socket s) {
		t = new Thread(this, "Client " + count);
		count++;
		this.s = s;
		try {
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
		} catch (IOException io) {
		}
		t.start();
	}

	public void run() {
		String msg = "";
		while (!msg.equals("bye")) {
			try {
				msg = in.readUTF();
				out.writeUTF(String.valueOf(msg.length()));
			} catch (IOException io) {
				System.out.println(io);
			}
		}
		System.out.println(t.getName() + " Released Connection");
		try {
			in.close();
			out.close();
			s.close();
		} catch (IOException io) {
		}
	}
}