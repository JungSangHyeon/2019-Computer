package device.motherBoard.interfaces;

import device.bus.Bus;
import device.motherBoard.adapter.CPUAdapter;
import device.motherBoard.adapter.MemoryAdapter;

public interface MemoryControllerInterface {
	void connectMemoryAdapter(MemoryAdapter...memoryAdapters);
	void connectCPUAdapter(CPUAdapter cpuAdapter);
	Bus getControlBus();
	Bus getIOBus();
	Bus getIOControlBus();
	void init();
	void run();
	void ioRun();
	int getAllMemorySize();
}
