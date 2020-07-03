package device.motherBoard.adapter;

import java.util.Vector;

import device.bus.Bus;
import device.motherBoard.interfaces.MemoryControllerInterface;

public class MemoryControllerAdapter implements MemoryControllerInterface{

	// Association
	private CPUAdapter cpuAdapter;
	private Vector<MemoryAdapter> memoryAdapters;
	private MemoryControllerInterface memoryController;
	private Bus controlBus, ioBus, ioControlBus;
	
	// Constructor
	public MemoryControllerAdapter() {this.memoryAdapters = new Vector<MemoryAdapter>();}
	
	@Override 
	public void init() {
		for(MemoryAdapter adapter : this.memoryAdapters) {this.memoryController.connectMemoryAdapter(adapter);}
		this.memoryController.connectCPUAdapter(this.cpuAdapter);
		this.controlBus = this.memoryController.getControlBus();
		this.ioBus = this.memoryController.getIOBus();
		this.ioControlBus = this.memoryController.getIOControlBus();
		this.memoryController.init();
	}
	
	@Override public void run() {this.memoryController.run();}
	@Override public void ioRun() {this.memoryController.ioRun();}
	
	// Getter & Setter
	public void connectMemoryController(MemoryControllerInterface memoryController) {this.memoryController=memoryController;}
	@Override public void connectCPUAdapter(CPUAdapter cpuAdapter) {this.cpuAdapter=cpuAdapter;}
	@Override public Bus getControlBus() {return this.controlBus;}
	@Override public Bus getIOBus() {return this.ioBus;}
	@Override public Bus getIOControlBus() {return this.ioControlBus;}
	@Override public void connectMemoryAdapter(MemoryAdapter... memoryAdapters) {
		for(MemoryAdapter memoryAdapter : memoryAdapters) {this.memoryAdapters.add(memoryAdapter);}
	}
	@Override public int getAllMemorySize() {return this.memoryController.getAllMemorySize();}
}
