package device.cpu.cu.cuInstruction.coreInstruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.memory.memoryInstruction.EMemoryInstruction;

public class Fetch extends CUInstruction{

	@Override
	public void excute() {
		this.mar.setData(this.pc.getData());
		this.memoryControllerAdapter.getControlBus().setValue(EMemoryInstruction.eCUFetch.ordinal());
		this.memoryControllerAdapter.run();
		this.ir.setData(this.mbr.getData());
	}
}
