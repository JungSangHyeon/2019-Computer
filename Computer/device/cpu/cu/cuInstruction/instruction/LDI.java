package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;

public class LDI extends CUInstruction{

	@Override
	public void excute() {
		int value = this.ir.getData() & 0x0000ffff;
		this.ac.setData(value);
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
