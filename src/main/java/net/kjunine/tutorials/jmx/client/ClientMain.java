package net.kjunine.tutorials.jmx.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.management.AttributeChangeNotification;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * First run ServerMain and run ClientMain later.
 * 
 * @author Daniel Ku
 * 
 */
public class ClientMain {

	public static class ClientListener implements NotificationListener {

		public void handleNotification(Notification notification,
				Object handback) {
			echo("\nReceived notification:");
			echo("\tClassName: " + notification.getClass().getName());
			echo("\tSource: " + notification.getSource());
			echo("\tType: " + notification.getType());
			echo("\tMessage: " + notification.getMessage());
			if (notification instanceof AttributeChangeNotification) {
				AttributeChangeNotification acn = (AttributeChangeNotification) notification;
				echo("\tAttributeName: " + acn.getAttributeName());
				echo("\tAttributeType: " + acn.getAttributeType());
				echo("\tNewValue: " + acn.getNewValue());
				echo("\tOldValue: " + acn.getOldValue());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		echo("\nCreate an RMI connector client and connect it to the RMI connector server");

		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

		echo("\nDomains:");
		String domains[] = mbsc.getDomains();
		Arrays.sort(domains);
		for (String domain : domains) {
			echo("\tDomain = " + domain);
		}

		echo("\nMBeanServer default domain = " + mbsc.getDefaultDomain());

		echo("\nMBean count = " + mbsc.getMBeanCount());
		echo("\nQuery MBeanServer MBeans:");
		Set<ObjectName> names = new TreeSet<ObjectName>(mbsc.queryNames(null,
				null));
		for (ObjectName name : names) {
			echo("\tObjectName = " + name);
		}

		waitForEnter();

		ClientListener listener = new ClientListener();

		ObjectName name = new ObjectName("com.example:type=ClientHello");
		Hello helloProxy = JMX.newMBeanProxy(mbsc, name, Hello.class, true);

		echo("\nAdd notification listener...");
		mbsc.addNotificationListener(name, listener, null, null);

		echo("\nCacheSize = " + helloProxy.getCacheSize());

		helloProxy.setCacheSize(150);

		echo("\nWaiting for notification...");
		sleep(1000);
		echo("\nCacheSize = " + helloProxy.getCacheSize());
		echo("\nInvoke sayHello() in Hello MBean...");
		helloProxy.sayHello();

		echo("\nInvoke add(2, 3) in Hello MBean...");
		echo("\nadd(2, 3) = " + helloProxy.add(2, 3));

		waitForEnter();

		name = new ObjectName("com.example:type=ClientQueueSampler");
		QueueSampler queueSamplerProxy = JMX.newMXBeanProxy(mbsc, name,
				QueueSampler.class);
		QueueSample queue1 = queueSamplerProxy.getQueueSample();
		echo("\nQueueSample.Date = " + queue1.getDate());
		echo("QueueSample.Head = " + queue1.getHead());
		echo("QueueSample.Size = " + queue1.getSize());
		echo("\nInvoke clearQueue() in QueueSampler MXBean...");
		queueSamplerProxy.clearQueue();

		QueueSample queue2 = queueSamplerProxy.getQueueSample();
		echo("\nQueueSample.Date = " + queue2.getDate());
		echo("QueueSample.Head = " + queue2.getHead());
		echo("QueueSample.Size = " + queue2.getSize());

		jmxc.close();
	}

	private static void echo(String msg) {
		System.out.println(msg);
	}

	private static void waitForEnter() throws Exception {
		System.out.println("Waiting until enter pressed...");
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	private static void sleep(int millis) throws Exception {
		Thread.sleep(millis);
	}

}
