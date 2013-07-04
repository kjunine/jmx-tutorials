package net.kjunine.tutorials.jmx.mbeans.mxbean;

import javax.management.MXBean;

@MXBean
public interface QueueSampler {

	public QueueSample getQueueSample();

	public void clearQueue();

}
