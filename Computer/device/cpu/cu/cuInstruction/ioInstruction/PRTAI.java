package device.cpu.cu.cuInstruction.ioInstruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.io.EIOID;

public class PRTAI extends CUInstruction{

	@Override
	public void excute() {
		int address = this.ir.getData() & 0x0000ffff;
		address += this.sp.getData();
		this.fetch(address);
		int data = this.mbr.getData();
		this.ioControllerAdapter.writeToIODevice(EIOID.eConsole, data);
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
