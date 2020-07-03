package device.cpu.cu.cuInstruction.coreInstruction;

import constant.Constant;
import constant.Constant.EInterruptID;
import constant.Constant.PCBIndex;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.cpu.status.StatusControl;
import device.cpu.status.StatusControl.EStatus;
import device.cpu.status.StatusControl.EStatusFlag;

public class ProcessInterrupt extends CUInstruction{

	@Override
	public void excute() {
		int interrupted = StatusControl.getStatusValue(EStatusFlag.eInterruptBit, this.status.getData());
		if(interrupted == EStatus.Interrupted.getValue()) {
			this.pc.setData(Constant.ISRStartAddress + 1);// Jump SETAC 
			
			if(this.ac.getData() != EInterruptID.processTerminated.ordinal()) {
				int intMasterStatusAddress = this.intMaster.getData() + PCBIndex.STATUS.ordinal(); 
				this.fetch(intMasterStatusAddress);
				int intMasterStatus = this.mbr.getData() & 0x0000ffff;
				int newStatus = StatusControl.getNewStatusValue(EStatusFlag.eInterruptBit, EStatus.NotInterrupted.getValue(), intMasterStatus);
				this.storeInstruction(this.intMaster.getData() + PCBIndex.STATUS.ordinal(), ECUInstruction.eSETSTA.ordinal(), newStatus);
			}
		}
	}
}
