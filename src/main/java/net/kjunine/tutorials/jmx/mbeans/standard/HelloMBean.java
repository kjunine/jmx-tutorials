package net.kjunine.tutorials.jmx.mbeans.standard;

public interface HelloMBean {

	public void sayHello();

	public int add(int x, int y);

	public String getName();

	public int getCacheSize();

	public void setCacheSize(int size);

}
