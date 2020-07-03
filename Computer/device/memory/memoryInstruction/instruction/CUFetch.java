package device.memory.memoryInstruction.instruction;

import constant.Constant;
import device.memory.memoryInstruction.MemoryInstruction;
import device.motherBoard.adapter.MemoryAdapter;

public class CUFetch extends MemoryInstruction{

	@Override
	public void excute() {
		int address = this.cpuAdapter.getMARBus().getData();
		MemoryAdapter now = this.memoryAdapters.get(address/Constant.MemorySize);
		this.cpuAdapter.getMBRBus().setValue(now.get(address - address/Constant.MemorySize*Constant.MemorySize));
		
		now.setNowUse(address - address/Constant.MemorySize*Constant.MemorySize);
		for(MemoryAdapter ma : this.memoryAdapters) {
			if(!ma.equals(now)) {ma.setNowUse(-1);}
		}
	}
}
