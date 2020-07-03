package device.motherBoard.adapter;

import device.motherBoard.interfaces.MemoryInterface;

public class MemoryAdapter implements MemoryInterface{

	// Associations
	private MemoryInterface memory;
	
	public void connectMemory(MemoryInterface memory) {this.memory=memory;}
	
	// Getter & Setter
	@Override public int get(int address) {return this.memory.get(address);}
	@Override public void set(int address, int data) {this.memory.set(address, data);}
	@Override public int getMemorySize() {return this.memory.getMemorySize();}
	@Override public void setNowUse(int i) {this.memory.setNowUse(i);}
}
