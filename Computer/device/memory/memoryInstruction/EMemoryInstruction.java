package device.memory.memoryInstruction;

import java.util.Vector;

import device.cpu.register.Register;
import device.memory.memoryInstruction.instruction.CUFetch;
import device.memory.memoryInstruction.instruction.Fetch;
import device.memory.memoryInstruction.instruction.IOFetch;
import device.memory.memoryInstruction.instruction.IOStore;
import device.memory.memoryInstruction.instruction.Store;
import device.motherBoard.adapter.CPUAdapter;
import device.motherBoard.adapter.MemoryAdapter;

public enum EMemoryInstruction {
	eFetch(new Fetch()),
	eStore(new Store()),
	eCUFetch(new CUFetch()),
	eIOStore(new IOStore()),
	eIOFetch(new IOFetch()),
	;
	
	// Components
	private MemoryInstruction instruction;
	
	// Constructor
	private EMemoryInstruction(MemoryInstruction instruction) {this.instruction=instruction;}
	public void logicalConnect(CPUAdapter cpuAdapter, Vector<MemoryAdapter> memoryAdapters, Register io) {this.instruction.initialize(cpuAdapter, memoryAdapters, io);}
	public void execute() {this.instruction.excute();}
}
