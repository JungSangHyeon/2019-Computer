package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;

public class LDAI extends CUInstruction{

	@Override
	public void excute() {
		int address = this.ir.getData() & 0x0000ffff;
		address += this.sp.getData();
		this.fetch(address);
		int data = this.mbr.getData();
		this.ac.setData(data);
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
