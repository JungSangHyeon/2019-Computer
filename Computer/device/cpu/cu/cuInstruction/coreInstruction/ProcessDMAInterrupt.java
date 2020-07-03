package device.cpu.cu.cuInstruction.coreInstruction;

import constant.Constant;
import constant.Constant.EInterruptID;
import device.cpu.cu.cuInstruction.CUInstruction;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.io.EIDataType;

public class ProcessDMAInterrupt extends CUInstruction{

	// Working Variable
	private boolean oneProcessed = false;
	
	@Override
	public void excute() {
		int nowExecute = this.pc.getData();
		if(nowExecute > Constant.APP_START_ADDRESS - 1 || nowExecute < Constant.ISRStartAddress) {// Interrupt 처리중엔 IO Interrupt는 대기.
			this.oneProcessed = false;	
			if(!this.oneProcessed) {this.processSPKFinish();}
			if(!this.oneProcessed) {this.processConsoleLoad();}
			if(!this.oneProcessed) {this.processMonitorLoad();}
		}
	}

	private void processSPKFinish() {
		this.fetch(EIDataType.eSPKFinish.getAddress());
		int spkFinished = this.mbr.getData() & 0x0000ffff;
		if(spkFinished == 1) {
			this.fetch(EIDataType.eSpeakerUser.getAddress());
			int speakerUserAddress = this.mbr.getData() & 0x0000ffff;
			this.intMaster.setData(speakerUserAddress);
			this.ac.setData(EInterruptID.restore.getID());
			this.pc.setData(Constant.ISRStartAddress + 1);// Jump SETAC 
			this.storeInstruction(EIDataType.eSPKFinish.getAddress(), ECUInstruction.eSPKFinish.ordinal(), 0);
			this.oneProcessed = true;
		}
	}
	
	private void processConsoleLoad() {
		this.fetch(EIDataType.eConsoleLoad.getAddress());
		int loadTargetID = this.mbr.getData() & 0x0000ffff;
		if(loadTargetID != 0) {//0 = null
			this.intMaster.setData(loadTargetID);
			this.ac.setData(EInterruptID.loadProcess.getID());
			this.pc.setData(Constant.ISRStartAddress + 1);// Jump SETAC 
			this.storeInstruction(EIDataType.eConsoleLoad.getAddress(), ECUInstruction.eConLoad.ordinal(), 0);
			this.oneProcessed = true;
		}
	}
	
	private void processMonitorLoad() {
		this.fetch(EIDataType.eMonitorLoad.getAddress());
		int monitorLoadTargetID = this.mbr.getData() & 0x0000ffff;
		if(monitorLoadTargetID != 0) {//0 = null
			this.intMaster.setData(monitorLoadTargetID);
			this.ac.setData(EInterruptID.loadProcess.getID());
			this.pc.setData(Constant.ISRStartAddress + 1);// Jump SETAC 
			this.storeInstruction(EIDataType.eMonitorLoad.getAddress(), ECUInstruction.eMoniLoad.ordinal(), 0);
			this.oneProcessed = true;
		}
	}
}
