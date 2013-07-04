package net.kjunine.tutorials.jmx.client;

import java.util.Date;
import java.util.Queue;

public class DefaultQueueSampler implements QueueSampler {

	private Queue<String> queue;

	public DefaultQueueSampler(Queue<String> queue) {
		this.queue = queue;
	}

	public QueueSample getQueueSample() {
		synchronized (queue) {
			return new QueueSample(new Date(), queue.size(), queue.peek());
		}
	}

	public void clearQueue() {
		synchronized (queue) {
			queue.clear();
		}
	}

}