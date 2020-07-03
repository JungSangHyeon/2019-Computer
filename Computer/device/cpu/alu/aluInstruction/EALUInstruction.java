package device.cpu.alu.aluInstruction;

import device.cpu.alu.aluInstruction.instruction.Add;
import device.cpu.alu.aluInstruction.instruction.Divide;
import device.cpu.alu.aluInstruction.instruction.Minus;
import device.cpu.alu.aluInstruction.instruction.Multifly;
import device.cpu.register.Register;

public enum EALUInstruction {
	eAdd(new Add()),
	eMinus(new Minus()),
	eMultifly(new Multifly()),
	eDivide(new Divide()),
	;
	
	// Components
	private ALUInstruction instruction;
	
	// Constructor
	private EALUInstruction(ALUInstruction instruction) {this.instruction=instruction;}
	public void logicalConnect(Register mbr, Register ac, Register status) {this.instruction.initialize(mbr, ac, status);}
	public void execute() {this.instruction.excute();}
}
