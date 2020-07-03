package device.cpu.cu.cuInstruction.interruptProcessInstruction;

import constant.Constant;
import constant.Constant.PCBIndex;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;

public class ProcessRestore extends CUInstruction{

	@Override
	public void excute() {
		int intMaster = this.intMaster.getData();
		
		this.fetch(Constant.PM_QUEUE_SIZE_ADDRESS);
		int size = this.mbr.getData();
		
		//size ++
		this.store(Constant.PM_QUEUE_SIZE_ADDRESS, size + 1);
		
		// store queue
		int lastQueueAddress = Constant.PM_QUEUE_SIZE_ADDRESS + size;
		int addAddress = lastQueueAddress + 1;
		this.store(addAddress, intMaster);
		
		this.pc.setData(Constant.ProcessManagerStartAddress);
		this.storeInstruction(Constant.ISRStartAddress + PCBIndex.PC.ordinal(), ECUInstruction.eSETPC.ordinal(), Constant.ISRStartAddress + Constant.pcbSize);
		this.storeInstruction(Constant.ISRStartAddress + PCBIndex.AC.ordinal() , ECUInstruction.eSETAC.ordinal(), 0);
	}
}
