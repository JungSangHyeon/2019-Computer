package device.cpu.cu.cuInstruction.ioInstruction;

import constant.Constant;
import constant.Constant.EInterruptID;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.cpu.status.StatusControl;
import device.cpu.status.StatusControl.EStatus;
import device.cpu.status.StatusControl.EStatusFlag;
import device.io.EIDataType;
import device.io.EIOID;

public class playSpk extends CUInstruction{

	@Override
	public void excute() {
		int time = this.ir.getData() & 0x0000ffff;
		this.ioControllerAdapter.writeToIODevice(EIOID.eSpeaker, time);
		this.storeInstruction(EIDataType.eSpeakerUser.getAddress(), ECUInstruction.eSPKUser.ordinal(), this.getProcessID()*Constant.processSize);
		
		this.ac.setData(EInterruptID.remove.getID());
		this.setInterruptMaster();
		this.status.setData(StatusControl.getNewStatusValue(EStatusFlag.eInterruptBit, EStatus.Interrupted.getValue(), this.status.getData()));
		
		this.pc.setData(this.pc.getData()+1);
		this.setPCB();
	}
}
