package device.motherBoard.adapter;

import java.util.Vector;

import device.io.EIOID;
import device.io.aIODevice.IODevice;
import device.io.ioController.IOController;
import device.motherBoard.interfaces.IOControllerInterface;

public class IOControllerAdapter implements IOControllerInterface{

	// Association
	private IOController ioController;
	private MemoryControllerAdapter memoryControllerAdapter;
	private HDDAdapter hddAdapter;
	
	// Component
	private Vector<IODevice> ioDevices;
	
	// Constructor
	public IOControllerAdapter() {this.ioDevices = new Vector<IODevice>();}
	
	@Override
	public void init() {
		for(IODevice ioDevice : this.ioDevices) {this.ioController.connectIODevice(ioDevice);}
		this.ioController.connectMemoryControllerAdapter(this.memoryControllerAdapter);
		this.ioController.connectHDDAdapter(this.hddAdapter);
		this.ioController.init();
	}
	
	// Getter & Setter
	public void connectIOController(IOController ioController) {this.ioController=ioController;}
	@Override public void connectIODevice(IODevice ioDevice) {this.ioDevices.add(ioDevice);}
	@Override public void connectMemoryControllerAdapter(MemoryControllerAdapter memoryControllerAdapter) {this.memoryControllerAdapter=memoryControllerAdapter;}
	@Override public void connectHDDAdapter(HDDAdapter hddAdapter) {this.hddAdapter=hddAdapter;}
	
	// Encapsulation
	@Override public void start() {this.ioController.start();}
	@Override public void writeToMemory(int address, int data) {this.ioController.writeToMemory(address, data);}
	@Override public int readFromMemory(int address) {return this.ioController.readFromMemory(address);}
	@Override public void writeToIODevice(EIOID id, int data) {this.ioController.writeToIODevice(id, data);}
}
