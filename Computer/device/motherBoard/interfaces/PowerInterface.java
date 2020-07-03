package device.motherBoard.interfaces;

import device.motherBoard.adapter.CPUAdapter;

public interface PowerInterface {
	void on();
	void connectCPUAdapter(CPUAdapter cpu);
}
