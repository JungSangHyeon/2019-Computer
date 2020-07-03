package device.memory.memoryInstruction.instruction;

import device.memory.memoryInstruction.MemoryInstruction;

public class IOStore extends MemoryInstruction{

	@Override
	public void excute() {
		int ioData = this.io.getData();
		int address = ioData>>>16;
		int value = ioData & 0x0000ffff;
		int nowData = this.memoryAdapters.get(0).get(address);
		int code = nowData & 0xffff0000;
		this.memoryAdapters.get(0).set(address, code+value);
	}
}
