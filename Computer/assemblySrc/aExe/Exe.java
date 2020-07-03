package assemblySrc.aExe;

import constant.Constant;
import device.cpu.cu.cuInstruction.ECUInstruction;

public class Exe {

	// Attributes
	private int[][] instructions;
	protected int pcbSize = Constant.pcbSize;
	protected int 
	// Null
	NULL = 0,
	
	// Symbol
	KeyCode = ECUInstruction.eKeyCode.ordinal(),
	MouseX = ECUInstruction.eMouseX.ordinal(),
	MouseY = ECUInstruction.eMouseY.ordinal(),
	MouseP = ECUInstruction.eMouseP.ordinal(),
	Freq = ECUInstruction.eFreq.ordinal(),
	SPKUser = ECUInstruction.eSPKUser.ordinal(),
	SPKFinish = ECUInstruction.eSPKFinish.ordinal(),
	ConLoad = ECUInstruction.eConLoad.ordinal(),
	MoniLoad = ECUInstruction.eMoniLoad.ordinal(),
	
	// GoTo
	GOTO = ECUInstruction.eGOTO.ordinal(), 
	GOTOACA = ECUInstruction.eGOTOACA.ordinal(), 
	GOTOABSA = ECUInstruction.eGOTOABSA.ordinal(),
	
	// Compare
	CMPAI = ECUInstruction.eCMPAI.ordinal(), 
	CMPI = ECUInstruction.eCMPI.ordinal(), 
	
	// Jump
	JLE = ECUInstruction.eJLE.ordinal(), 
	JLEE = ECUInstruction.eJLEE.ordinal(),
	JNEQ = ECUInstruction.eJNEQ.ordinal(), 
	
	// Load
    LDACAI = ECUInstruction.eLDACAI.ordinal(), 
	LDAI = ECUInstruction.eLDAI.ordinal(), 
	LDI = ECUInstruction.eLDI.ordinal(), 
	
	// Store
	STA = ECUInstruction.eSTA.ordinal(),
	
	// Print
	PRTAC = ECUInstruction.ePRTAC.ordinal(), 
	PRTAI = ECUInstruction.ePRTAI.ordinal(), 
	
	// Calculate
	ADDAI = ECUInstruction.eADDAI.ordinal(), 
	ADDI = ECUInstruction.eADDI.ordinal(),
	MINUSI = ECUInstruction.eMINUSI.ordinal(),
	
	// Thread
	MakeThread = ECUInstruction.eMakeThread.ordinal(), 
			
	// SW Interrupt
	HALT = ECUInstruction.eHALT.ordinal(), 
			
	// Interrupt Process
	ProcessTerminatedProcess = ECUInstruction.eTerminated.ordinal(),
	ProcessTimeOut = ECUInstruction.eTimeOut.ordinal(),
	ProcessRemove = ECUInstruction.eRemove.ordinal(),
	ProcessRestore = ECUInstruction.eRestore.ordinal(),
	LoadProcess = ECUInstruction.eLoadProcess.ordinal(),
	
	// IO
	SetFreq = ECUInstruction.eSetFreq.ordinal(),
	PlaySpk = ECUInstruction.ePlaySpk.ordinal()
	;

	// Getter & Setter
	public void setInstructions(int[][] instructions) {this.instructions = instructions;}
	public int[][] getInstructions() {return this.instructions;}
}
