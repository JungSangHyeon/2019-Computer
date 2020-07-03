package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;

public class STA extends CUInstruction{

	@Override
	public void excute() {
		int address = this.ir.getData() & 0x0000ffff;
		address += this.sp.getData();
		this.store(address, this.ac.getData());
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
