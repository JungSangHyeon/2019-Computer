package device.cpu.cu.cuInstruction.instruction;

import assemblySrc.aExe.Exe;
import constant.Constant;
import constant.Constant.PCBIndex;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.memory.memoryInstruction.EMemoryInstruction;

public class MakeThread extends CUInstruction{

	@Override
	public void excute() {
		int processMemoryAddress = this.getEmptyMemoryArea();
		if(processMemoryAddress!=-1) {
			int exeID = this.ir.getData() & 0x0000ffff;
			Exe exe = this.hddAdapter.getExe(exeID);
			int[][] instructions = exe.getInstructions();
			this.storeInstruction(processMemoryAddress + PCBIndex.AC.ordinal(), ECUInstruction.eSETAC.ordinal(), 0);// PCB
			this.storeInstruction(processMemoryAddress + PCBIndex.STATUS.ordinal(), ECUInstruction.eSETSTA.ordinal(), 0);
			this.storeInstruction(processMemoryAddress + PCBIndex.CP.ordinal(), ECUInstruction.eSETCP.ordinal(), processMemoryAddress);
			this.storeInstruction(processMemoryAddress + PCBIndex.SP.ordinal(), ECUInstruction.eSETSP.ordinal(), processMemoryAddress + instructions.length + Constant.pcbSize);
			this.storeInstruction(processMemoryAddress + PCBIndex.PC.ordinal(), ECUInstruction.eSETPC.ordinal(), processMemoryAddress + Constant.pcbSize);
			for(int i =0; i<instructions.length; i++) {this.storeInstruction(processMemoryAddress+Constant.pcbSize+i, instructions[i][0], instructions[i][1]);}// Code
		}
		
		this.fetch(Constant.PM_QUEUE_SIZE_ADDRESS);
		int size = this.mbr.getData();
		
		//size ++
		this.store(Constant.PM_QUEUE_SIZE_ADDRESS, size + 1);
		
		// store queue
		int lastQueueAddress = Constant.PM_QUEUE_SIZE_ADDRESS + size;
		int addAddress = lastQueueAddress + 1;
		this.store(addAddress, processMemoryAddress);
		
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
	
	public int getEmptyMemoryArea() {
		for(int i=0; i<this.memoryControllerAdapter.getAllMemorySize(); i+=Constant.processSize) {
			this.mar.setData(i);
			this.memoryControllerAdapter.getControlBus().setValue(EMemoryInstruction.eFetch.ordinal());
			this.memoryControllerAdapter.run();
			if(this.mbr.getData() == 0) {return i;}
		}
		return -1; // Memory Full Error
	}
}
