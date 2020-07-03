package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.status.StatusControl;
import device.cpu.status.StatusControl.EStatus;
import device.cpu.status.StatusControl.EStatusFlag;

public class JNEQ extends CUInstruction{

	@Override
	public void excute() {
		int address = (this.ir.getData() & 0x0000ffff) + this.cp.getData();
		int zeroBitValue = StatusControl.getStatusValue(EStatusFlag.eZeroBit, this.status.getData());
		
		if(zeroBitValue == EStatus.ZeroFalse.getValue()) {this.pc.setData(address);} // Not Zero -> Not Equal
		else if (zeroBitValue == EStatus.ZeroTrue.getValue()) {this.pc.setData(this.pc.getData()+1);} // Zero -> Equal
		this.setPCB();
	} 
}
