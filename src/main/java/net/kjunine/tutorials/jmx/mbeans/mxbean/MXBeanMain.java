package net.kjunine.tutorials.jmx.mbeans.mxbean;

import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MXBeanMain {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = new ObjectName("com.example:type=QueueSampler");

		Queue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Request-1");
		queue.add("Request-2");
		queue.add("Request-3");
		DefaultQueueSampler queueSampler = new DefaultQueueSampler(queue);

		mbs.registerMBean(queueSampler, name);

		System.out.println("Waiting...");
		Thread.sleep(Long.MAX_VALUE);
	}

}
