package device.cpu.cu.cuInstruction.coreInstruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;

public class Execute extends CUInstruction{

	@Override
	public void excute() {
		int instruction = this.ir.getData();
		int code = instruction>>>16;
		ECUInstruction.values()[code].execute();
	}
}
