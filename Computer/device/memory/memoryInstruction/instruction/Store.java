package device.memory.memoryInstruction.instruction;

import constant.Constant;
import device.memory.memoryInstruction.MemoryInstruction;

public class Store extends MemoryInstruction{

	@Override
	public void excute() {
		int address = this.cpuAdapter.getMARBus().getData();
		int data = this.cpuAdapter.getMBRBus().getData();
		this.memoryAdapters.get(address/Constant.MemorySize).set(address- address/Constant.MemorySize*Constant.MemorySize, data);
	}
}
