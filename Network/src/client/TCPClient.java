package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) {
		
		try {
			Socket client =  new Socket("127.0.0.1", 8080);
			OutputStream out = client.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF("您好,服务端!");
			
			InputStream in = client.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			String msg = dis.readUTF();
			System.out.println("服务端响应消息:" + msg);
			dos.close();
			out.close();
			client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
