package device.motherBoard.interfaces;

public interface MemoryInterface {
	int get(int address);
	void set(int address, int data);
	int getMemorySize();
	void setNowUse(int i);
}
