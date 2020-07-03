package device.io;

import constant.Constant;

public enum EIDataType { // DMA와 맞출것.
	eKeyCode,
	eMouseX, 
	eMouseY, 
	eMousePress,
	eSpeakerFreq,
	eSpeakerUser,
	eSPKFinish,
	eConsoleLoad,
	eMonitorLoad,
	;
	
	public int getAddress() {return Constant.DMAStartAddress + Constant.pcbSize + this.ordinal();}
}
