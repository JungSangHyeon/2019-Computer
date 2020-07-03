package device.motherBoard.adapter;

import device.bus.Bus;
import device.cpu.register.Register;
import device.motherBoard.interfaces.CPUInterface;

public class CPUAdapter implements CPUInterface{

	// Associations
	private CPUInterface cpu;
	private MemoryControllerAdapter memoryControllerAdapter;
	private HDDAdapter hddAdapter;
	private ROMAdapter romAdapter;
	private IOControllerAdapter ioControllerAdapter;
	private Register status;
	private Bus marBus, mbrBus;
	
	@Override public void run() {this.cpu.run();}
	@Override public void init() {
		this.marBus = this.cpu.getMARBus();
		this.mbrBus = this.cpu.getMBRBus();
		this.status = this.cpu.getStatus();
		this.cpu.connectMemoryControllerAdapter(this.memoryControllerAdapter);
		this.cpu.connectHDDAdapter(this.hddAdapter);
		this.cpu.connectIOControllerAdapter(this.ioControllerAdapter);
		this.cpu.connectROMAdapter(this.romAdapter);
		this.cpu.init();
	}
	
	// Encapsulation
	@Override public Bus getMARBus() {return this.marBus;}
	@Override public Bus getMBRBus() {return this.mbrBus;}
	@Override public Register getStatus() {return this.status;}
	
	// Getter & Setter
	public void connectCPU(CPUInterface cpu) {this.cpu = cpu;}
	@Override public void connectHDDAdapter(HDDAdapter hddAdapter) {this.hddAdapter=hddAdapter;}
	@Override public void connectIOControllerAdapter(IOControllerAdapter ioControllerAdapter) {this.ioControllerAdapter=ioControllerAdapter;}
	@Override public void connectMemoryControllerAdapter(MemoryControllerAdapter memoryControllerAdapter) {this.memoryControllerAdapter=memoryControllerAdapter;}
	@Override public void connectROMAdapter(ROMAdapter romAdapter) {this.romAdapter=romAdapter;}
}
