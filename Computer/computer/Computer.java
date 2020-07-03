package computer;

import java.util.Vector;

import device.aDevice.Device;
import device.cpu.CPU;
import device.hdd.HDD;
import device.io.ioController.IOController;
import device.io.ioDevice.Console;
import device.io.ioDevice.Keyboard;
import device.io.ioDevice.Monitor;
import device.io.ioDevice.Mouse;
import device.io.ioDevice.Speaker;
import device.memory.Memory;
import device.memory.MemoryController;
import device.motherBoard.MotherBoard;
import device.power.Power;
import device.rom.ROM;

public class Computer {

	// Components
	private HDD hdd;
	private CPU cpu;
	private Power power;
	private Memory memory1, memory2;
	private MotherBoard motherBoard;
	private MemoryController memoryController;
	private IOController ioController;
	private Console console;
	private Monitor monitor;
	private Keyboard keyBoard;
	private Mouse mouse;
	private Speaker speaker;
	private ROM rom;
	
	// Constructor
	public Computer() {
		// Create Components
		this.hdd = new HDD();
		this.cpu = new CPU();
		this.power = new Power();
		this.memory1 = new Memory(1180, 1);
		this.memory2 = new Memory(1180 + 410 + 100, 2);
		this.motherBoard = new MotherBoard();
		this.memoryController = new MemoryController();
		this.ioController = new IOController();
		this.console = new Console();
		this.monitor = new Monitor();
		this.keyBoard = new Keyboard();
		this.mouse = new Mouse();
		this.speaker = new Speaker();
		this.rom = new ROM();
		
		// Assemble Components
		this.motherBoard.connectHDD(this.hdd);
		this.motherBoard.connectCPU(this.cpu);
		this.motherBoard.connectPower(this.power);
		this.motherBoard.connectMemory(1, this.memory1);
		this.motherBoard.connectMemory(2, this.memory2);
		this.motherBoard.connectMemoryController(this.memoryController);
		this.motherBoard.connectIOController(this.ioController);
		this.motherBoard.connectIODevice(this.console);
		this.motherBoard.connectIODevice(this.monitor);
		this.motherBoard.connectIODevice(this.keyBoard);
		this.motherBoard.connectIODevice(this.mouse);
		this.motherBoard.connectIODevice(this.speaker);
		this.motherBoard.connectROM(this.rom);
	}
	
	public void init() {this.motherBoard.init(); this.motherBoard.start();}
	public void powerOn() {this.power.on();}
	
	// Getter & Setter
	public Keyboard getKeyboard() {return this.keyBoard;}
	public Mouse getMouse() {return this.mouse;}
	public Vector<Device> getDevices() {
		Device[] devices = new Device[] {this.motherBoard, this.hdd, this.cpu, this.power, this.memory1, this.memory2, this.memoryController, this.ioController, this.monitor, this.console, this.keyBoard, this.mouse, this.rom};
		Vector<Device> result = new Vector<Device>();
		for(Device device : devices) {result.add(device);}
		return result;
	}
}
