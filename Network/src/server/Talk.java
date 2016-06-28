package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Talk extends Thread {
	
	private Socket client = null;
	public Talk(Socket client){
		this.client = client;
	}

	@Override
	public void run() {
		//获取ip
		InetAddress ip = client.getInetAddress();
		//获取输入流收取消息
		InputStream in = null;
		DataInputStream dis = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		try {
			in = client.getInputStream();
			dis = new DataInputStream(in);
			
			//获得输出流用于向客户端发出消息
			out = client.getOutputStream();
			dos = new DataOutputStream(out);
			
			while( true ){
			
				String msg = dis.readUTF();
				if( msg == null || "bye".equalsIgnoreCase(msg) ){
					dos.writeUTF("您好,客户端,即将退出聊天");
					break;
				}
				System.out.println("从" + ip.getHostAddress() + "收到的消息:" + msg);
				
				dos.writeUTF("您好,客户端,您发的消息是:" + msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(  dos != null ) dos.close();
				if( out != null ) out.close();
				if( dis != null )dis.close();
				if( in != null) in.close();
				if( client != null) client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
