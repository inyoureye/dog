package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try {
			
			Socket client =  new Socket("127.0.0.1", 8080);
			OutputStream out = client.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			System.out.print("msg:");
			String msg = input.next();
			
			dos.writeUTF( msg );
			
			InputStream in = client.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			String msg2 = dis.readUTF();
			System.out.println("服务端响应消息:" + msg2);
			dos.close();
			out.close();
			client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
