package device.memory.memoryInstruction.instruction;

import constant.Constant;
import device.memory.memoryInstruction.MemoryInstruction;

public class Fetch extends MemoryInstruction{

	@Override
	public void excute() {
		int address = this.cpuAdapter.getMARBus().getData();
		this.cpuAdapter.getMBRBus().setValue(this.memoryAdapters.get(address/Constant.MemorySize).get(address- address/Constant.MemorySize*Constant.MemorySize));
	}
}
