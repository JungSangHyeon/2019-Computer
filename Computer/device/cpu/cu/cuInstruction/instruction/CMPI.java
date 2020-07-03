package device.cpu.cu.cuInstruction.instruction;

import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.status.StatusControl;
import device.cpu.status.StatusControl.EStatus;
import device.cpu.status.StatusControl.EStatusFlag;

public class CMPI extends CUInstruction{

	@Override
	public void excute() {
		int parameter = this.ir.getData() & 0x0000ffff;
		int cmpResult = this.ac.getData() - parameter;
		
		if(cmpResult == 0) {this.status.setData(StatusControl.getNewStatusValue(EStatusFlag.eZeroBit, EStatus.ZeroTrue.getValue(), this.status.getData()));}
		else if(cmpResult < 0) {this.status.setData(StatusControl.getNewStatusValue(EStatusFlag.eSignBit, EStatus.SignMinus.getValue(), this.status.getData()));}
		else if(cmpResult > 0) {this.status.setData(StatusControl.getNewStatusValue(EStatusFlag.eSignBit, EStatus.SignPlus.getValue(), this.status.getData()));}
		if(cmpResult != 0) {this.status.setData(StatusControl.getNewStatusValue(EStatusFlag.eZeroBit, EStatus.ZeroFalse.getValue(), this.status.getData()));}
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
//CMP A = AC - A
//if(0){AC < A}
//if(1){AC > A}