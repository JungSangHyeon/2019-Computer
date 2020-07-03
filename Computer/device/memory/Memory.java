package device.memory;

import constant.Constant;
import device.aDevice.Device;
import device.motherBoard.interfaces.MemoryInterface;
import visualizer.MemoryVisualizer;

public class Memory extends Device implements MemoryInterface{

	// Attributes
	private int size = Constant.MemorySize;
	
	// Components
	private int[] realMemory;
	private MemoryVisualizer memoryVisualizer;
	
	// Constructor
	public Memory(int y, int n) {
		this.realMemory = new int[this.size];
		this.memoryVisualizer = new MemoryVisualizer(this.realMemory, y, n);
		this.setVisualizer(this.memoryVisualizer);
	}
	
	// Getter & Setter
	@Override public int getMemorySize() {return this.realMemory.length;}
	@Override public int get(int address) {return this.realMemory[address];}
	@Override public void set(int address, int data) {this.realMemory[address] = data;}
	@Override public void setNowUse(int i) {this.memoryVisualizer.setNowUse(i);}
}
