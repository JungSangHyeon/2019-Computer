package device.cpu;

import constant.Constant;
import device.cpu.cu.CU;

public class Clock extends Thread{

	// Attributes
	private int clockSpeed = Constant.ClockSpeed; // ms
	
	// Associations
	private CU cu;
	
	// Working Variable
	private boolean on = false;
	
	@Override
	public void run() {
		this.on = true;
		while(this.on) {
			new CuRunThread().run();
			this.sleep(this.clockSpeed);
		}
	}
	
	// Getter & Setter
	public void connectCU(CU cu) {this.cu=cu;}
	public void setSpeed(int speed) {this.clockSpeed=speed;}
	
	// Encapsulation
	private void sleep(int ms) {try {Thread.sleep(ms);} catch (InterruptedException e) {e.printStackTrace();}}
	
	// Inner Class
	private class CuRunThread extends Thread {@Override public void run() {cu.run();}}
}
