package device.motherBoard.interfaces;

import device.bus.Bus;
import device.cpu.register.Register;
import device.motherBoard.adapter.HDDAdapter;
import device.motherBoard.adapter.IOControllerAdapter;
import device.motherBoard.adapter.MemoryControllerAdapter;
import device.motherBoard.adapter.ROMAdapter;

public interface CPUInterface {
	public Bus getMARBus();
	public Bus getMBRBus();
	public Register getStatus();
	public void connectMemoryControllerAdapter(MemoryControllerAdapter memoryControllerAdapter);
	public void connectHDDAdapter(HDDAdapter hddAdapter);
	public void connectIOControllerAdapter(IOControllerAdapter ioControllerAdapter);
	public void connectROMAdapter(ROMAdapter romAdapter);
	public void init();
	public void run();
}
