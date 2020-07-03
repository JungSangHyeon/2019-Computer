package device.cpu.cu.cuInstruction.instruction;

import device.cpu.alu.aluInstruction.EALUInstruction;
import device.cpu.cu.cuInstruction.CUInstruction;

public class ADDAI extends CUInstruction{

	@Override
	public void excute() {
		int address = this.ir.getData() & 0x0000ffff;
		address += this.sp.getData();
		this.fetch(address);
		this.alu.run(EALUInstruction.eAdd.ordinal());
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
