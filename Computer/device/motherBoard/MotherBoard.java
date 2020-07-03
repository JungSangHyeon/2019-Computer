package device.motherBoard;

import device.aDevice.Device;
import device.cpu.CPU;
import device.hdd.HDD;
import device.io.aIODevice.IODevice;
import device.io.ioController.IOController;
import device.memory.Memory;
import device.memory.MemoryController;
import device.motherBoard.adapter.CPUAdapter;
import device.motherBoard.adapter.HDDAdapter;
import device.motherBoard.adapter.IOControllerAdapter;
import device.motherBoard.adapter.MemoryAdapter;
import device.motherBoard.adapter.MemoryControllerAdapter;
import device.motherBoard.adapter.PowerAdapter;
import device.motherBoard.adapter.ROMAdapter;
import device.power.Power;
import device.rom.ROM;

public class MotherBoard extends Device{

	// Components
	private CPUAdapter cpuAdapter;
	private PowerAdapter powerAdapter;
	private MemoryAdapter memoryAdapter1, memoryAdapter2;
	private MemoryControllerAdapter memoryControllerAdapter;
	private HDDAdapter hddAdapter;
	private IOControllerAdapter ioControllerAdapter;
	private ROMAdapter romAdapter;
	
	// Constructor
	public MotherBoard() {
		// Create Components
		this.cpuAdapter = new CPUAdapter();
		this.powerAdapter = new PowerAdapter();
		this.memoryAdapter1 = new MemoryAdapter();
		this.memoryAdapter2 = new MemoryAdapter();
		this.memoryControllerAdapter = new MemoryControllerAdapter();
		this.hddAdapter = new HDDAdapter();
		this.ioControllerAdapter = new IOControllerAdapter();
		this.romAdapter = new ROMAdapter();
		
		// Assemble Components
		this.cpuAdapter.connectROMAdapter(this.romAdapter);
		this.cpuAdapter.connectMemoryControllerAdapter(this.memoryControllerAdapter);
		this.cpuAdapter.connectHDDAdapter(this.hddAdapter);
		this.cpuAdapter.connectIOControllerAdapter(this.ioControllerAdapter);
		this.powerAdapter.connectCPUAdapter(this.cpuAdapter);
		this.memoryControllerAdapter.connectCPUAdapter(this.cpuAdapter);
		this.memoryControllerAdapter.connectMemoryAdapter(this.memoryAdapter1, this.memoryAdapter2);
		this.ioControllerAdapter.connectMemoryControllerAdapter(this.memoryControllerAdapter);
		this.ioControllerAdapter.connectHDDAdapter(this.hddAdapter);
	}

	public void init() {
		this.powerAdapter.init();
		this.ioControllerAdapter.init();
		this.memoryControllerAdapter.init();
		this.cpuAdapter.init();
	}
	
	public void start() {this.ioControllerAdapter.start();}
	
	// Getter & Setter
	public void connectCPU(CPU cpu) {this.cpuAdapter.connectCPU(cpu);}
	public void connectPower(Power power) {this.powerAdapter.connectPower(power);}
	public void connectHDD(HDD hdd) {this.hddAdapter.connectHDD(hdd);}
	public void connectROM(ROM rom) {this.romAdapter.connectROM(rom);}
	public void connectIOController(IOController ioController) {this.ioControllerAdapter.connectIOController(ioController);}
	public void connectIODevice(IODevice ioDevice) {this.ioControllerAdapter.connectIODevice(ioDevice);}
	public void connectMemoryController(MemoryController memoryController) {this.memoryControllerAdapter.connectMemoryController(memoryController);}
	public void connectMemory(int adapterNum, Memory memory) {
		if(adapterNum == 1) {this.memoryAdapter1.connectMemory(memory);}
		else if(adapterNum == 2) {this.memoryAdapter2.connectMemory(memory);}
	}
}
