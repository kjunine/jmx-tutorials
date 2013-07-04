package net.kjunine.tutorials.jmx.mbeans.mxbean;

import java.util.Date;

public class QueueSample {

	private final Date date;
	private final int size;
	private final String head;

	public QueueSample(Date date, int size, String head) {
		this.date = date;
		this.size = size;
		this.head = head;
	}

	public Date getDate() {
		return date;
	}

	public int getSize() {
		return size;
	}

	public String getHead() {
		return head;
	}

}