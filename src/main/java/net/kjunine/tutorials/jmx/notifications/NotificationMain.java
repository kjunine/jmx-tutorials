package net.kjunine.tutorials.jmx.notifications;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class NotificationMain {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("com.example:type=NotificationHello");
		DefaultHello hello = new DefaultHello();
		mbs.registerMBean(hello, name);

		System.out.println("Waiting forever...");
		Thread.sleep(Long.MAX_VALUE);
	}

}
