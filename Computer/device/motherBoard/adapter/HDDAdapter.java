package device.motherBoard.adapter;

import assemblySrc.aExe.Exe;
import device.hdd.HDD;
import device.motherBoard.interfaces.HDDInterface;

public class HDDAdapter implements HDDInterface {
	
	// Associate
	private HDD hdd;
	
	public void connectHDD(HDD hdd) {this.hdd=hdd;}

	// Getter & Setter
	@Override public Exe getExe(int id) {return this.hdd.getExe(id);}
	@Override public int getExeID(String name) {return this.hdd.getExeID(name);}
	@Override public String getName(int id) {return this.hdd.getName(id);}
}
