package device.cpu.cu.cuInstruction.interruptProcessInstruction;

import constant.Constant;
import constant.Constant.PCBIndex;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;

public class ProcessTimeOut extends CUInstruction{

	@Override
	public void excute() {
		this.pc.setData(Constant.ProcessManagerStartAddress);
		this.storeInstruction(Constant.ISRStartAddress + PCBIndex.PC.ordinal(), ECUInstruction.eSETPC.ordinal(), Constant.ISRStartAddress + Constant.pcbSize);
		this.storeInstruction(Constant.ISRStartAddress + PCBIndex.AC.ordinal() , ECUInstruction.eSETAC.ordinal(), 0);
	}
}
