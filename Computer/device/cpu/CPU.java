package device.cpu;

import device.aDevice.Device;
import device.bus.Bus;
import device.cpu.alu.ALU;
import device.cpu.alu.aluInstruction.EALUInstruction;
import device.cpu.cu.CU;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.cpu.register.Register;
import device.motherBoard.adapter.HDDAdapter;
import device.motherBoard.adapter.IOControllerAdapter;
import device.motherBoard.adapter.MemoryControllerAdapter;
import device.motherBoard.adapter.ROMAdapter;
import device.motherBoard.interfaces.CPUInterface;
import visualizer.cpuVisualizer.CPUVisualizer;

public class CPU extends Device implements CPUInterface{

	// Association
	private MemoryControllerAdapter memoryControllerAdapter;
	private HDDAdapter hddAdapter;
	private IOControllerAdapter ioControllerAdapter;
	private ROMAdapter romAdapter;
	
	// Components
	private Clock clock; private CU cu; private ALU alu;
	private Register ir, pc, cp, sp, mar, mbr, ac, status, intMaster;
	private CPUVisualizer cpuVisualizer;
	
	// Constructor
	public CPU() {
		// Create Components
		this.clock = new Clock(); this.cu = new CU(); this.alu = new ALU(); 
		this.ir = new Register(); this.pc = new Register();  this.cp = new Register(); this.sp = new Register(); this.mar = new Register();
		this.mbr = new Register(); this.ac = new Register(); this.status = new Register(); this.intMaster = new Register();
		this.cpuVisualizer = new CPUVisualizer();
		this.setVisualizer(this.cpuVisualizer);
		
		// Assemble Components
		this.clock.connectCU(this.cu);
	}
	
	@Override
	public void init() {
		for(ECUInstruction instruction : ECUInstruction.values()) {instruction.logicalConnect(this.alu, this.ir, this.pc, this.cp, this.sp, this.mar, this.mbr, this.ac, this.status, this.intMaster, this.memoryControllerAdapter, this.hddAdapter, this.ioControllerAdapter);}
		for(EALUInstruction instruction : EALUInstruction.values()) {instruction.logicalConnect(this.mbr, this.ac, this.status);}
		this.cpuVisualizer.init(this.ir, this.pc, this.cp, this.sp, this.mar, this.mbr, this.ac, this.status, this.intMaster);
		this.cu.executeBIOS(this.romAdapter); 
	}
	
	@Override public void run() {this.clock.run();}
	
	// Getter & Setter
	@Override public Bus getMARBus() {return new Bus(this.mar);}
	@Override public Bus getMBRBus() {return new Bus(this.mbr);}
	@Override public Register getStatus() {return this.status;}
	@Override public void connectMemoryControllerAdapter(MemoryControllerAdapter memoryControllerAdapter) {this.memoryControllerAdapter=memoryControllerAdapter;}
	@Override public void connectHDDAdapter(HDDAdapter hddAdapter) {this.hddAdapter=hddAdapter;}
	@Override public void connectIOControllerAdapter(IOControllerAdapter ioControllerAdapter) {this.ioControllerAdapter=ioControllerAdapter;}
	@Override public void connectROMAdapter(ROMAdapter romAdapter) {this.romAdapter=romAdapter;}
}
