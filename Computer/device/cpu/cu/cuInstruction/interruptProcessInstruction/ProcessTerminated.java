package device.cpu.cu.cuInstruction.interruptProcessInstruction;

import constant.Constant;
import constant.Constant.PCBIndex;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;

public class ProcessTerminated extends CUInstruction{

	@Override
	public void excute() {
		this.fetch(Constant.PM_QUEUE_INDEX_ADDRESS);
		int deleteTargetAddress = this.mbr.getData() + Constant.PM_QUEUE_INDEX_ADDRESS + Constant.PM_QUEUE_START_ADDRESS;
		
		this.fetch(Constant.PM_QUEUE_SIZE_ADDRESS);
		int size = this.mbr.getData();
		
		int lastAddress = Constant.PM_QUEUE_SIZE_ADDRESS + size;
		this.fetch(lastAddress);
		int lastData = this.mbr.getData();
		
		this.store(deleteTargetAddress, lastData);
		this.store(lastAddress, 0);
		this.store(Constant.PM_QUEUE_SIZE_ADDRESS, size - 1);
				
		// ISR Reset
		this.pc.setData(Constant.ProcessManagerStartAddress);
		this.storeInstruction(Constant.ISRStartAddress + PCBIndex.PC.ordinal(), ECUInstruction.eSETPC.ordinal(), Constant.ISRStartAddress + Constant.pcbSize);
		this.storeInstruction(Constant.ISRStartAddress + PCBIndex.AC.ordinal(), ECUInstruction.eSETAC.ordinal(), 0);
	}
}
