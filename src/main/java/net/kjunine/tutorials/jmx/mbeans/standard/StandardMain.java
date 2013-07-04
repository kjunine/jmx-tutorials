package net.kjunine.tutorials.jmx.mbeans.standard;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class StandardMain {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("com.example:type=Hello");
		Hello hello = new Hello();
		mbs.registerMBean(hello, name);

		System.out.println("Waiting forever...");
		Thread.sleep(Long.MAX_VALUE);
	}

}
