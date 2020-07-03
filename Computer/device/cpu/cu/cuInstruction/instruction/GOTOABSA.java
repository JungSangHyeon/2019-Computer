package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;

public class GOTOABSA extends CUInstruction{

	@Override
	public void excute() {// AC 안의 절대주소로 이동.
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
		this.pc.setData(this.ac.getData());
	}
}
