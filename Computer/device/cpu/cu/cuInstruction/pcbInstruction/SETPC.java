package device.cpu.cu.cuInstruction.pcbInstruction;

import device.cpu.cu.cuInstruction.CUInstruction;

public class SETPC extends CUInstruction{

	@Override
	public void excute() {
		int data = this.ir.getData() & 0x0000ffff;
		this.pc.setData(data);
	}
}
