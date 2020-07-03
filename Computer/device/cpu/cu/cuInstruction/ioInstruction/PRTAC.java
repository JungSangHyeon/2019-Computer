package device.cpu.cu.cuInstruction.ioInstruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.io.EIOID;

public class PRTAC extends CUInstruction{

	@Override
	public void excute() {
		this.ioControllerAdapter.writeToIODevice(EIOID.eConsole, this.ac.getData());
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
