package device.memory.memoryInstruction.instruction;

import device.memory.memoryInstruction.MemoryInstruction;

public class IOFetch extends MemoryInstruction{

	@Override
	public void excute() {
		int ioData = this.io.getData();
		int address = ioData>>>16;
		int value = this.memoryAdapters.get(0).get(address) & 0x0000ffff;
		this.io.setData(value);
	}
}
