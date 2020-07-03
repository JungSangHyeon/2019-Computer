package device.cpu.cu.cuInstruction.instruction;

import device.cpu.alu.aluInstruction.EALUInstruction;
import device.cpu.cu.cuInstruction.CUInstruction;

public class ADDI extends CUInstruction{

	@Override
	public void excute() {
		int value = this.ir.getData() & 0x0000ffff;
		this.mbr.setData(value);
		this.alu.run(EALUInstruction.eAdd.ordinal());
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
