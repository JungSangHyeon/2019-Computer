package device.cpu.cu.cuInstruction.instruction;

import constant.Constant;
import constant.Constant.EInterruptID;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.cpu.status.StatusControl;
import device.cpu.status.StatusControl.EStatus;
import device.cpu.status.StatusControl.EStatusFlag;

public class HALT extends CUInstruction{

	@Override
	public void excute() {
		int processStartAddress = this.cp.getData();
		for(int i = 0; i< Constant.processSize; i++) {this.storeInstruction(processStartAddress + i, ECUInstruction.eNULL.ordinal(), 0);}
		
		this.ac.setData(EInterruptID.processTerminated.getID());
		this.setInterruptMaster();
		this.status.setData(StatusControl.getNewStatusValue(EStatusFlag.eInterruptBit, EStatus.Interrupted.getValue(), this.status.getData()));
	}
}
