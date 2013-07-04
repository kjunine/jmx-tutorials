package net.kjunine.tutorials.jmx.client;

import javax.management.MXBean;

@MXBean
public interface QueueSampler {

	public QueueSample getQueueSample();

	public void clearQueue();

}
