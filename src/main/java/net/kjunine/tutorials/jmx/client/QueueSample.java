package net.kjunine.tutorials.jmx.client;

import java.beans.ConstructorProperties;
import java.util.Date;

import javax.management.openmbean.CompositeData;

public class QueueSample {

	private final Date date;
	private final int size;
	private final String head;

	/**
	 * For JMX client to construct this class from CompositeData.
	 * 
	 * Option 1. Use @ConstructorProperties.
	 * 
	 * @param date
	 * @param size
	 * @param head
	 */
	@ConstructorProperties({ "date", "size", "head" })
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

	/**
	 * For JMX client to construct this class from CompositeData.
	 * 
	 * Option 2. Use from(CompositeData) method.
	 * 
	 * @param data
	 * @return
	 */
	public static QueueSample from(CompositeData data) {
		if (data == null) {
			return null;
		}

		Date date = (Date) data.get("date");
		int size = (Integer) data.get("size");
		String head = (String) data.get("head");

		return new QueueSample(date, size, head);
	}

}