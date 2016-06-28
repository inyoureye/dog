package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Socket client = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		InputStream in = null;
		DataInputStream dis = null;
		try {
			
			client =  new Socket("127.0.0.1", 8080);
			out = client.getOutputStream();
			dos = new DataOutputStream(out);
			
			
			
			in = client.getInputStream();
			dis = new DataInputStream(in);
			while( true ){
				System.out.print("msg:");
				String msg = input.next();
				
				dos.writeUTF( msg );
				
				String msg2 = dis.readUTF();
				System.out.println("服务端响应消息:" + msg2);
				
				if( "bye".equalsIgnoreCase( msg )){
					break;
				}
			}
			
			System.out.println("已退出聊天");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dis.close();
				in.close();
				
				dos.close();
				out.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
