package device.cpu.cu.cuInstruction.interruptProcessInstruction;

import constant.Constant;
import constant.Constant.PCBIndex;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;

public class ProcessRemove extends CUInstruction{

	@Override
	public void excute() {
		int intMaster = this.intMaster.getData();
		
		//size --
		this.fetch(Constant.PM_QUEUE_SIZE_ADDRESS);
		int size = this.mbr.getData();
		this.store(Constant.PM_QUEUE_SIZE_ADDRESS, size - 1);
		
		//find&remove intMaster
		int intMasterQueueAddress = 0;
		for(int i = 0; i < size; i++) {
			int nowQueueAddress = Constant.PM_QUEUE_SIZE_ADDRESS + i + 1;
			this.fetch(nowQueueAddress);
			if(this.mbr.getData() == intMaster) {intMasterQueueAddress = nowQueueAddress; break;}
		}
		
		int lastQueueAddress = Constant.PM_QUEUE_SIZE_ADDRESS + size;
		if(intMasterQueueAddress == lastQueueAddress) {
			this.store(lastQueueAddress, 0);
		}else {
			this.fetch(lastQueueAddress);
			this.store(intMasterQueueAddress, this.mbr.getData());
			this.store(lastQueueAddress, 0);
		}
		this.pc.setData(Constant.ProcessManagerStartAddress);
		this.storeInstruction(Constant.ISRStartAddress + PCBIndex.PC.ordinal(), ECUInstruction.eSETPC.ordinal(), Constant.ISRStartAddress + Constant.pcbSize);
		this.storeInstruction(Constant.ISRStartAddress + PCBIndex.AC.ordinal() , ECUInstruction.eSETAC.ordinal(), 0);
	}
}
