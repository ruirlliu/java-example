package example.framework.spring.bootstrap;


import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LogDemo {

	public static void main(String[] args) throws UnknownHostException {
		String hostName = InetAddress.getLocalHost().getHostName();
		System.out.println(hostName);
		String jvmName = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println(jvmName);
	}

}
