package device.cpu.cu.cuInstruction.pcbInstruction;

import device.cpu.cu.cuInstruction.CUInstruction;

public class SETAC extends CUInstruction{

	@Override
	public void excute() {
		int data = this.ir.getData() & 0x0000ffff;
		this.ac.setData(data);
		this.pc.setData(this.pc.getData()+1);
	}
}
