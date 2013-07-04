package net.kjunine.tutorials.jmx.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import net.kjunine.tutorials.jmx.mbeans.mxbean.DefaultQueueSampler;
import net.kjunine.tutorials.jmx.notifications.DefaultHello;

/**
 * First run ServerMain and run ClientMain later.
 * 
 * @author Daniel Ku
 * 
 */
public class ServerMain {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("com.example:type=ClientHello");
		DefaultHello hello = new DefaultHello();
		mbs.registerMBean(hello, name);

		name = new ObjectName("com.example:type=ClientQueueSampler");
		Queue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Request-1");
		queue.add("Request-2");
		queue.add("Request-3");
		DefaultQueueSampler queueSampler = new DefaultQueueSampler(queue);
		mbs.registerMBean(queueSampler, name);

		waitForEnter();
	}

	private static void waitForEnter() throws Exception {
		System.out.println("Waiting until enter pressed...");
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

}
