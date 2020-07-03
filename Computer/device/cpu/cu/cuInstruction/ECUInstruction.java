package device.cpu.cu.cuInstruction;

import device.cpu.alu.ALU;
import device.cpu.cu.cuInstruction.coreInstruction.BIOS;
import device.cpu.cu.cuInstruction.coreInstruction.Decode;
import device.cpu.cu.cuInstruction.coreInstruction.Execute;
import device.cpu.cu.cuInstruction.coreInstruction.Fetch;
import device.cpu.cu.cuInstruction.coreInstruction.LoadCore;
import device.cpu.cu.cuInstruction.coreInstruction.NULL;
import device.cpu.cu.cuInstruction.coreInstruction.ProcessDMAInterrupt;
import device.cpu.cu.cuInstruction.coreInstruction.ProcessInterrupt;
import device.cpu.cu.cuInstruction.coreInstruction.ProcessTimeOutInterrupt;
import device.cpu.cu.cuInstruction.coreInstruction.TIMER;
import device.cpu.cu.cuInstruction.instruction.ADDAI;
import device.cpu.cu.cuInstruction.instruction.ADDI;
import device.cpu.cu.cuInstruction.instruction.CMPAI;
import device.cpu.cu.cuInstruction.instruction.CMPI;
import device.cpu.cu.cuInstruction.instruction.GOTO;
import device.cpu.cu.cuInstruction.instruction.GOTOABSA;
import device.cpu.cu.cuInstruction.instruction.GOTOACA;
import device.cpu.cu.cuInstruction.instruction.HALT;
import device.cpu.cu.cuInstruction.instruction.JLE;
import device.cpu.cu.cuInstruction.instruction.JLEE;
import device.cpu.cu.cuInstruction.instruction.JNEQ;
import device.cpu.cu.cuInstruction.instruction.LDACAI;
import device.cpu.cu.cuInstruction.instruction.LDAI;
import device.cpu.cu.cuInstruction.instruction.LDI;
import device.cpu.cu.cuInstruction.instruction.MINUSI;
import device.cpu.cu.cuInstruction.instruction.MakeThread;
import device.cpu.cu.cuInstruction.instruction.STA;
import device.cpu.cu.cuInstruction.interruptProcessInstruction.LoadProcess;
import device.cpu.cu.cuInstruction.interruptProcessInstruction.ProcessRemove;
import device.cpu.cu.cuInstruction.interruptProcessInstruction.ProcessRestore;
import device.cpu.cu.cuInstruction.interruptProcessInstruction.ProcessTerminated;
import device.cpu.cu.cuInstruction.interruptProcessInstruction.ProcessTimeOut;
import device.cpu.cu.cuInstruction.ioInstruction.PRTAC;
import device.cpu.cu.cuInstruction.ioInstruction.PRTAI;
import device.cpu.cu.cuInstruction.ioInstruction.playSpk;
import device.cpu.cu.cuInstruction.ioInstruction.setFreq;
import device.cpu.cu.cuInstruction.pcbInstruction.SETAC;
import device.cpu.cu.cuInstruction.pcbInstruction.SETCP;
import device.cpu.cu.cuInstruction.pcbInstruction.SETPC;
import device.cpu.cu.cuInstruction.pcbInstruction.SETSP;
import device.cpu.cu.cuInstruction.pcbInstruction.SETSTATUS;
import device.cpu.cu.cuInstruction.symbolInstruction.ConLoad;
import device.cpu.cu.cuInstruction.symbolInstruction.Freq;
import device.cpu.cu.cuInstruction.symbolInstruction.KeyCode;
import device.cpu.cu.cuInstruction.symbolInstruction.MoniLoad;
import device.cpu.cu.cuInstruction.symbolInstruction.MouseP;
import device.cpu.cu.cuInstruction.symbolInstruction.MouseX;
import device.cpu.cu.cuInstruction.symbolInstruction.MouseY;
import device.cpu.cu.cuInstruction.symbolInstruction.SPKFinish;
import device.cpu.cu.cuInstruction.symbolInstruction.SPKUser;
import device.cpu.register.Register;
import device.motherBoard.adapter.HDDAdapter;
import device.motherBoard.adapter.IOControllerAdapter;
import device.motherBoard.adapter.MemoryControllerAdapter;

public enum ECUInstruction {
	/*Null*/eNULL(new NULL()), // NULL Ordinal Must Be 0.
	/*BIOS*/eBIOS(new BIOS()),
	/*PCB*/eSETCP(new SETCP()), eSETSP(new SETSP()), eSETPC(new SETPC()), eSETAC(new SETAC()), eSETSTA(new SETSTATUS()),
	/*OS*/eLoadProcess(new LoadProcess()), eLoadCore(new LoadCore()),
	/*CU Core - Interrupt*/eProcessDMAInterrupt(new ProcessDMAInterrupt()), eProcessInterrupt(new ProcessInterrupt()), eProcessTimeOutInterrupt(new ProcessTimeOutInterrupt()),
	/*CU Core - Run*/eFetch(new Fetch()), eDecode(new Decode()), eExecute(new Execute()),
	/*Keyboard Symbol*/eKeyCode(new KeyCode()), 
	/*Mouse Symbol*/eMouseX(new MouseX()), eMouseY(new MouseY()), eMouseP(new MouseP()),
	/*Speaker Symbol*/eFreq(new Freq()), eSPKUser(new SPKUser()), eSPKFinish(new SPKFinish()),
	/*Monitor Symbol*/eMoniLoad(new MoniLoad()),
	/*Console Symbol*/eConLoad(new ConLoad()),
	/*GoTo*/eGOTO(new GOTO()), eGOTOACA(new GOTOACA()), eGOTOABSA(new GOTOABSA()),
	/*Compare*/eCMPAI(new CMPAI()), eCMPI(new CMPI()),
	/*Jump*/eJLE(new JLE()), eJLEE(new JLEE()), eJNEQ(new JNEQ()),
	/*Load*/eLDACAI(new LDACAI()), eLDAI(new LDAI()), eLDI(new LDI()),
	/*Store*/eSTA(new STA()),
	/*Print*/ePRTAC(new PRTAC()), ePRTAI(new PRTAI()),
	/*Calculate*/eADDAI(new ADDAI()), eADDI(new ADDI()), eMINUSI(new MINUSI()),
	/*Thread*/eMakeThread(new MakeThread()),
	/*SW Interrupt*/eHALT(new HALT()), eTIMER(new TIMER()),
	/*Interrupt Process*/eTerminated(new ProcessTerminated()), eTimeOut(new ProcessTimeOut()), eRemove(new ProcessRemove()), eRestore(new ProcessRestore()),
	/*IO*/eSetFreq(new setFreq()), ePlaySpk(new playSpk()),
	;
	
	// Components
	private CUInstruction instruction;
	
	// Constructor
	private ECUInstruction(CUInstruction instruction) {this.instruction=instruction;}
	public void logicalConnect(ALU alu, Register ir, Register pc, Register cp, Register sp, Register mar, Register mbr, Register ac, Register status, Register intMaster, MemoryControllerAdapter memoryControllerAdapter, HDDAdapter hddAdapter, IOControllerAdapter ioControllerAdapter) {
		this.instruction.initialize(alu, ir, pc, cp, sp, mar, mbr, ac, status, intMaster, memoryControllerAdapter, hddAdapter, ioControllerAdapter);
	}
	public void execute() {this.instruction.excute();}
}
