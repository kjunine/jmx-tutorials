package net.kjunine.tutorials.jmx.notifications;

import javax.management.MXBean;

@MXBean
public interface Hello {

	public void sayHello();

	public int add(int x, int y);

	public String getName();

	public int getCacheSize();

	public void setCacheSize(int size);

}
