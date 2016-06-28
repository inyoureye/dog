package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.security.auth.callback.Callback;

public class TCPServer3 {
	private static ExecutorService exec = Executors.newFixedThreadPool(100);
	
	public static void main(String[] args) {
		try {
			//启动一个serverSocket监听8080端口
			ServerSocket server = new ServerSocket(8080);
			
			//Executor exec = Executors.newFixedThreadPool(100);
			//CompletionService<String> cs = new ExecutorCompletionService<>(exec);
			while( ! exec.isShutdown() ){
				//阻塞等待客户端连接
				Socket client = server.accept();
				
				//new Talk( client ).start();
				exec.execute(new Talk( client ) );
				
				/*cs.submit(new Callable<String>() {
					
					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						//do......
						return "";
					}
				});*/
			}
			
			//Future<String> r = cs.take();
			//String result = r.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ finally {
			
		}
		

	}
	
	public static void stop(){
		
		exec.shutdown();
		//exec.shutdownNow();
	}

}


/*Runnable task = new Runnable() {
@Override
public void run() {
	// TODO Auto-generated method stub
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
};
new Thread( task ).start();*/
