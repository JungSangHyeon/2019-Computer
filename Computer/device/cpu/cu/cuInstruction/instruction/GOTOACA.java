package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;

public class GOTOACA extends CUInstruction{

	@Override
	public void excute() {// Process안의 어딘가로 감.
		int value = (this.ac.getData() & 0x0000ffff) + this.cp.getData();
		this.pc.setData(value);
		this.setPCB();
	}
}
