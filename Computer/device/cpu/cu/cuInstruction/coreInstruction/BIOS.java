package device.cpu.cu.cuInstruction.coreInstruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.hdd.HDD.EExe;

public class BIOS extends CUInstruction{

	@Override
	public void excute() {
		this.ir.setData(EExe.eProcessManager.ordinal());
		ECUInstruction.eLoadCore.execute();
		
		this.ir.setData(EExe.eISR.ordinal());
		ECUInstruction.eLoadCore.execute();
		
		this.ir.setData(EExe.eIODataMemory.ordinal());
		ECUInstruction.eLoadCore.execute();
	}
}
