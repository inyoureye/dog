package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("www.baidu.com");
			
			System.out.println(ia.getHostName());
			System.out.println(ia.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
