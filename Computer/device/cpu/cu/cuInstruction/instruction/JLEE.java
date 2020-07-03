package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.status.StatusControl;
import device.cpu.status.StatusControl.EStatus;
import device.cpu.status.StatusControl.EStatusFlag;

public class JLEE extends CUInstruction{

	@Override
	public void excute() {
		int value = (this.ir.getData() & 0x0000ffff) + this.cp.getData();
		
		int zeroBitValue = StatusControl.getStatusValue(EStatusFlag.eZeroBit, this.status.getData());
		if(zeroBitValue == EStatus.ZeroTrue.getValue()) {this.pc.setData(value);}
		else {
			int signBitValue = StatusControl.getStatusValue(EStatusFlag.eSignBit, this.status.getData());
			if(signBitValue == EStatus.SignMinus.getValue()) {this.pc.setData(value);} // Minus : AC < CMP Value
			else if (signBitValue == EStatus.SignPlus.getValue()) {this.pc.setData(this.pc.getData()+1);} // Plus : AC > CMP Value
		}
		this.setPCB();
	} 
}
