package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;

public class LDACAI extends CUInstruction{

	@Override
	public void excute() {
		int address = this.ac.getData();
		address += this.sp.getData();
		this.fetch(address);
		int data = this.mbr.getData();
		this.ac.setData(data);
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
