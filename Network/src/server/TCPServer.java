package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		try {
			//启动一个serverSocket监听8080端口
			ServerSocket server = new ServerSocket(8080);
			//阻塞等待客户端连接
			Socket client = server.accept();
			
			//获取ip
			InetAddress ip = client.getInetAddress();
			//获取输入流收取消息
			InputStream in = client.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			String msg = dis.readUTF();
			
			System.out.println("从" + ip.getHostAddress() + "收到的消息:" + msg);
			//获得输出流用于向客户端发出消息
			OutputStream out = client.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF("您好,客户端");
			
			dos.close();
			out.close();
			
			dis.close();
			in.close();
			client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
