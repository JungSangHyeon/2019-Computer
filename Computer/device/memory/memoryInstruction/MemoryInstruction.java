package device.memory.memoryInstruction;

import java.util.Vector;

import device.cpu.register.Register;
import device.motherBoard.adapter.CPUAdapter;
import device.motherBoard.adapter.MemoryAdapter;

public abstract class MemoryInstruction {

	// Association
	protected CPUAdapter cpuAdapter;
	protected Vector<MemoryAdapter> memoryAdapters;
	protected Register io;
	
	public void initialize(CPUAdapter cpuAdapter, Vector<MemoryAdapter> memoryAdapters, Register io) {
		this.cpuAdapter=cpuAdapter;
		this.memoryAdapters=memoryAdapters;
		this.io=io;
	}

	// Abstract Method
	public abstract void excute();
}
