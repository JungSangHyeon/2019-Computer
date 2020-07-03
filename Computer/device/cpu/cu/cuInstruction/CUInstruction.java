package device.cpu.cu.cuInstruction;

import constant.Constant;
import constant.Constant.PCBIndex;
import device.cpu.alu.ALU;
import device.cpu.register.Register;
import device.memory.memoryInstruction.EMemoryInstruction;
import device.motherBoard.adapter.HDDAdapter;
import device.motherBoard.adapter.IOControllerAdapter;
import device.motherBoard.adapter.MemoryControllerAdapter;

public abstract class CUInstruction {

	// Associations
	protected ALU alu;
	protected Register ir, pc, cp, sp, mar, mbr, ac, status, intMaster;
	protected MemoryControllerAdapter memoryControllerAdapter;
	protected HDDAdapter hddAdapter;
	protected IOControllerAdapter ioControllerAdapter;
	
	public void initialize(ALU alu, Register ir, Register pc, Register cp, Register sp, Register mar, Register mbr, Register ac, Register status, Register interrupt, MemoryControllerAdapter memoryControllerAdapter, HDDAdapter hddAdapter, IOControllerAdapter ioControllerAdapter) {
		this.alu = alu; this.ir = ir; this.pc = pc; this.cp = cp; this.sp = sp; this.mar = mar; this.mbr = mbr; this.ac = ac; this.status = status; this.intMaster = interrupt;
		this.memoryControllerAdapter = memoryControllerAdapter;
		this.hddAdapter = hddAdapter;
		this.ioControllerAdapter = ioControllerAdapter;
	}

	protected void fetch(int address){
		this.mar.setData(address);
		this.memoryControllerAdapter.getControlBus().setValue(EMemoryInstruction.eFetch.ordinal());
		this.memoryControllerAdapter.run();
	}

	protected void store(int address, int data){
		this.mar.setData(address); this.mbr.setData(data);
		this.memoryControllerAdapter.getControlBus().setValue(EMemoryInstruction.eStore.ordinal());
		this.memoryControllerAdapter.run();
	}

	protected void storeInstruction(int address, int code, int parameter){
		int instruction = (code<<16)+parameter;
		this.store(address, instruction);
	}
	
	// Getter & Setter
	public void setInterruptMaster() {this.intMaster.setData(this.getProcessID()*Constant.processSize);}
	protected int getProcessID() {return this.pc.getData() / Constant.processSize;}
	protected void setPCB() {
		int marTemp = this.mar.getData();
		int pcbACAddress = (this.pc.getData()/Constant.processSize)*Constant.processSize + PCBIndex.AC.ordinal();
		this.storeInstruction(pcbACAddress, ECUInstruction.eSETAC.ordinal(), this.ac.getData());
		int pcbSTATUSAddress = (this.pc.getData()/Constant.processSize)*Constant.processSize + PCBIndex.STATUS.ordinal();
		this.storeInstruction(pcbSTATUSAddress, ECUInstruction.eSETSTA.ordinal(), this.status.getData());
		int pcbPCAddress = (this.pc.getData()/Constant.processSize)*Constant.processSize + PCBIndex.PC.ordinal();
		this.storeInstruction(pcbPCAddress, ECUInstruction.eSETPC.ordinal(), this.pc.getData());
		this.mar.setData(marTemp);
	}
	
	// Abstract Method
	public abstract void excute();
}
