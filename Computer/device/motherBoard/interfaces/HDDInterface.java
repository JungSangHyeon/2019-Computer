package device.motherBoard.interfaces;

import assemblySrc.aExe.Exe;

public interface HDDInterface {
	Exe getExe(int id);
	int getExeID(String name);
	String getName(int id);
}
