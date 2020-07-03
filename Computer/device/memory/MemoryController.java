package device.memory;

import java.util.Vector;

import device.aDevice.Device;
import device.bus.Bus;
import device.cpu.register.Register;
import device.memory.memoryInstruction.EMemoryInstruction;
import device.motherBoard.adapter.CPUAdapter;
import device.motherBoard.adapter.MemoryAdapter;
import device.motherBoard.interfaces.MemoryControllerInterface;
import visualizer.MCVisualizer;

public class MemoryController extends Device implements MemoryControllerInterface{

	// Attributes
	private int allMemorySize = 0;
	
	// Association
	private CPUAdapter cpuAdapter;
	private Vector<MemoryAdapter> memoryAdapters;
	
	// Components
	private Register control, io, ioControl;
		
	// Constructor
	public MemoryController() {
		this.control = new Register();
		this.io = new Register();
		this.ioControl = new Register();
		this.memoryAdapters = new Vector<MemoryAdapter>();
		this.setVisualizer(new MCVisualizer());
	}
	
	public void init() {
		for(EMemoryInstruction instruction : EMemoryInstruction.values()) {instruction.logicalConnect(this.cpuAdapter, this.memoryAdapters, this.io);}
	}
	
	@Override public void run() {this.runInstruction(this.control.getData());}
	@Override public void ioRun() {this.runInstruction(this.ioControl.getData());}
	
	private void runInstruction(int nowInstructionID) {
		for(EMemoryInstruction instruction : EMemoryInstruction.values()) {
			if(nowInstructionID == instruction.ordinal()) {instruction.execute(); break;}
		}
	}
	
	// Getter & Setter
	@Override public void connectMemoryAdapter(MemoryAdapter... memoryAdapters) {
		for(MemoryAdapter memoryAdapter : memoryAdapters) {this.memoryAdapters.add(memoryAdapter);}
		this.setMemorySize();
	}
	private void setMemorySize() {for(MemoryAdapter adapter : this.memoryAdapters) {this.allMemorySize += adapter.getMemorySize();}}
	@Override public int getAllMemorySize() {return this.allMemorySize;}
	@Override public void connectCPUAdapter(CPUAdapter cpuAdapter) {this.cpuAdapter = cpuAdapter;}
	@Override public Bus getControlBus() {return new Bus(this.control);}
	@Override public Bus getIOBus() {return new Bus(this.io);}
	@Override public Bus getIOControlBus() {return new Bus(this.ioControl);}
}
