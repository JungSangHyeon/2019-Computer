package device.cpu.alu;

import device.cpu.alu.aluInstruction.EALUInstruction;

public class ALU {

	public void run(int instructionID) {
		for(EALUInstruction instruction : EALUInstruction.values()) {
			if(instructionID == instruction.ordinal()) {instruction.execute(); break;}
		}
	}
}
