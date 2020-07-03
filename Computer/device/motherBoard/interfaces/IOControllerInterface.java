package device.motherBoard.interfaces;

import device.io.EIOID;
import device.io.aIODevice.IODevice;
import device.motherBoard.adapter.HDDAdapter;
import device.motherBoard.adapter.MemoryControllerAdapter;

public interface IOControllerInterface {
	void writeToMemory(int address, int data);
	int readFromMemory(int address);
	void writeToIODevice(EIOID id, int data);
	void connectIODevice(IODevice ioDevice);
	void connectMemoryControllerAdapter(MemoryControllerAdapter memoryControllerAdapter);
	void connectHDDAdapter(HDDAdapter hddAdapter);
	void init();
	void start();
}
