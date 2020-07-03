package device.bus;

import device.cpu.register.Register;

public class Bus {

	// Component
	private Register register;
	
	// Constructor
	public Bus(Register register) {this.register=register;}
	
	// Getter & Setter
	public int getData() {return this.register.getData();}
	public void setValue(int value) {this.register.setData(value);}
}
