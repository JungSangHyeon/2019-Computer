package device.cpu.cu.cuInstruction.coreInstruction;

import constant.Constant;
import constant.Constant.EInterruptID;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.status.StatusControl;
import device.cpu.status.StatusControl.EStatus;
import device.cpu.status.StatusControl.EStatusFlag;

public class TIMER extends CUInstruction{

	// Attribute
	private int nowN = 0;
	
	@Override
	public void excute() {
		int nowExecute = this.pc.getData();
		if(nowExecute > Constant.APP_START_ADDRESS - 1) {this.nowN++;}
		else {this.nowN= 0;}
		if(this.nowN == Constant.TIME_OUT_LINE_NUM) {
			this.nowN= 0;
			this.ac.setData(EInterruptID.timeOut.getID());
			this.setInterruptMaster();
			this.status.setData(StatusControl.getNewStatusValue(EStatusFlag.eInterruptBit, EStatus.Interrupted.getValue(), this.status.getData()));
		}
	}
}
