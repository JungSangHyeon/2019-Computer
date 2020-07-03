package device.io.aIODevice;

import device.aDevice.Device;
import device.io.EIOID;
import device.io.ioController.IOController;
import event.ATimer;

public abstract class IODevice extends Device{

	// Attribute
	protected EIOID id;
	
	// Association
	protected IOController ioController;
	
	// Component
	private IOClock ioClock;
	
	// Constructor
	public IODevice(int clockTime) {this.ioClock = new IOClock(clockTime);}
	public void init(IOController ioController) {this.ioController=ioController;}
	public void start() {this.ioClock.startTimer();}
	
	// Getter & Setter
	public boolean is(EIOID id) {return this.id.equals(id);}
	
	// Abstract Method
	public abstract void process();
	public abstract void addData(int data);
	
	// Inner Class
	private class IOClock extends ATimer {
		public IOClock(int time) {super(time, true);}
		@Override protected void timeOutTask() {process();}
	}
}
