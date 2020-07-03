package device.cpu.cu.cuInstruction.ioInstruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.io.EIDataType;

public class setFreq extends CUInstruction{

	@Override
	public void excute() {
		int freq = this.ir.getData() & 0x0000ffff;
		this.storeInstruction(EIDataType.eSpeakerFreq.getAddress(), ECUInstruction.eFreq.ordinal(), freq);
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
