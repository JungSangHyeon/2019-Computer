package device.cpu.alu.aluInstruction.instruction;

import device.cpu.alu.aluInstruction.ALUInstruction;

public class Divide extends ALUInstruction{

	@Override
	public void excute() {
		int value = this.mbr.getData();
		int calculatedValue = this.ac.getData() / value;
		this.ac.setData(calculatedValue);
	}
}
