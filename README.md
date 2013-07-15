JMX Tutorials
=============

This is a repository for a collection of JMX tutorials.

These tutorials are originated from the official Java Tutorial document which you can find [here](http://docs.oracle.com/javase/tutorial/jmx/index.html).

And of course, there is [Spring JMX](http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/jmx.html), yeah.

And here are summaries of these materials.


The official JMX Tutorial
=========================

The Overview of the JMX Technology
----------------------------------

The JMX technology provides **a simple, standard way of managing resources** such as applications, devices, and services.

The JMX specification defines the **architecture**, **design patterns**, **APIs**, and **services** in the Java programming language for _management and monitoring of applications and networks_.

### Components ###

* Managed Beans, MBeans
* JMX agent
	* MBean server
	* services for handling the MBeans
	* JMX connectors


### Why Use the JMX Technology? ###

Just do it.

### Architecture of the JMX Technology ###

#### Instrumentation ####

* **MBean**

	Manageable resources shoud be _instrumented by MBeans_, so they can be managed through a JMX agent.

* _MXBean_

	A special kind of MBean that references only a pre-defined set of data types.

#### JMX Agent ####

* **JMX agent**

	A standard management agent that _directly controls resources_ and makes them available to remote management applications.
	(Usually located with the resources.)

* **MBean server**

	The core component of a JMX agent.
	A managed object server in which MBeans are registered.

#### Remote Management ####

The MBean server _relies on protocol adaptors and connectors_ to make a JMX agent accessible from management applications outside the agent's JVM. But **each connector provides the same remogte management interface** through a different protocol.

### Monitoring and Management of the Java Virtual Machine ###

#### Out-of-the-box management tools for the JVM ####

The JVM has **built-in instrumentation** that enables you to monitor and manage it by providing these:

* platform MXBeans
* platform MBean server

#### JConsole (jconsole) ####

A monitoring and management tool included in JDK. Use **VisualVM (jvisualvm)**. It is way much better.

_Notice_: Applications that you want to monitor need to be started with the following option.

	-Dcom.sun.management.jmxremote

But if you use (the official Sun/Oracle) Java SE 6/7 HotSpot VM (supports the Attach API), it does not need to be started using the above option.


Introducing MBeans
-------------------

* **MBean** again

	A managed Java object, similar to a JavaBeans component, that follows the design patterns set forth in the JMX specification. The JMX specification defines five types of MBean:

	* Standard MBeans
	* Dynamic MBeans
	* Open MBeans
	* Model MBeans
	* MXBeans

### Standard MBeans ###

* **Standard MBean**

	A standard MBean is defined by an interface called _Something_**MBean** and a class called _Something_ that implements that interface. _All properties and methods_ declared in the interface will be exposed as **attributes and operations**.

For example sources, see next package.

	net.kjunine.tutorials.jmx.mbeans.standard

### MXBeans ###

* **MXBean**

	A type of MBean that references only a predefined set of data types.
	
	An MXBean is defined by an interface called _Something_**MXBean** and a class called _Something_ that implements that interface.

* **@MXBean annotation**

	The annotation **@MXBean** can be also used to annotate the inteface, instead of requiring the interface's name to be followed by the MXBean suffix.

For example sources, see next package.

	net.kjunine.tutorials.jmx.mbeans.mxbean


Notifications
-------------

To generate notifications, an MBean must implement the inteface _NotificationEmitter_ or **extend NotificationBroadcasterSupport**. Use this method:

	NotificationBroadcasterSupport.sendNotification(Notification)

For example sources, see next package.

	net.kjunine.tutorials.jmx.notifications


Remote Management
-----------------

### Exposing a Resource for Remote Management ###

To expose your application for remote management, you need to start it with the correct properties:

	java \
	-Dcom.sun.management.jmxremote.port=9999 \
	-Dcom.sun.management.jmxremote.authenticate=false \
	-Dcom.sun.management.jmxremote.ssl=false \
	MAIN_CLASS

### Creating a Custom JMX Client ###

The client uses a **JMXConnector** class for which it will need a _JMXConnectorFactory_ and a _JMXServiceURL_.

For example sources, see next package.

	net.kjunine.tutorials.jmx.client
