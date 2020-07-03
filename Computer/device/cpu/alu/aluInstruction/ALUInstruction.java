package device.cpu.alu.aluInstruction;

import device.cpu.register.Register;

public abstract class ALUInstruction {

	// Association
	protected Register mbr, ac, status;
	
	public void initialize(Register mbr, Register ac, Register status) {this.mbr=mbr; this.ac=ac; this.status=status;}

	// Abstract Method
	public abstract void excute();
}
