package device.io.ioController;

import java.util.Vector;
import java.util.concurrent.Semaphore;

import device.aDevice.Device;
import device.io.EIOID;
import device.io.aIODevice.IODevice;
import device.memory.memoryInstruction.EMemoryInstruction;
import device.motherBoard.adapter.HDDAdapter;
import device.motherBoard.adapter.MemoryControllerAdapter;
import device.motherBoard.interfaces.IOControllerInterface;
import visualizer.IOCVisualizer;

public class IOController extends Device implements IOControllerInterface{

	// Association
	private MemoryControllerAdapter memoryControllerAdapter;
	private HDDAdapter hddAdapter;
	
	// Component
	private Vector<IODevice> ioDevices;
	private Semaphore semaphore;
	
	// Constructor
	public IOController() {
		this.ioDevices = new Vector<IODevice>();
		this.semaphore = new Semaphore(1);
		this.setVisualizer(new IOCVisualizer());
	} 
	@Override public void init() {for(IODevice ioDevice : this.ioDevices) {ioDevice.init(this);}}
	@Override public void start() {for(IODevice ioDevice : this.ioDevices) {ioDevice.start();}}
	
	@Override
	public void writeToMemory(int address, int data) { // DMA : mouse, keyboard
		int ioData = (address<<16)+data;		
		this.memoryControllerAdapter.getIOBus().setValue(ioData);
		this.memoryControllerAdapter.getIOControlBus().setValue(EMemoryInstruction.eIOStore.ordinal());
		this.memoryControllerAdapter.ioRun();
	}

	@Override
	public int readFromMemory(int address) {
		int ioData = address<<16;		
		this.memoryControllerAdapter.getIOBus().setValue(ioData);
		this.memoryControllerAdapter.getIOControlBus().setValue(EMemoryInstruction.eIOFetch.ordinal());
		this.memoryControllerAdapter.ioRun();
		return this.memoryControllerAdapter.getIOBus().getData();
	}
	
	@Override
	public void writeToIODevice(EIOID id, int data) {
		for(IODevice ioDevice : this.ioDevices) {if(ioDevice.is(id)) {ioDevice.addData(data);}}
	}

	// Getter & Setter
	public void acquire() {try {this.semaphore.acquire();} catch (InterruptedException e) {e.printStackTrace();}}
	public void release() {this.semaphore.release();}
	public int getExeID(String name) {return this.hddAdapter.getExeID(name);}
	@Override public void connectIODevice(IODevice ioDevice) {this.ioDevices.add(ioDevice);}
	@Override public void connectMemoryControllerAdapter(MemoryControllerAdapter memoryControllerAdapter) {this.memoryControllerAdapter=memoryControllerAdapter;}
	@Override public void connectHDDAdapter(HDDAdapter hddAdapter) {this.hddAdapter=hddAdapter;}
}
