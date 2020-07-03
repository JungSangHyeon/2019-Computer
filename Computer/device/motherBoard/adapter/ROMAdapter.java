package device.motherBoard.adapter;

import device.cpu.cu.cuInstruction.ECUInstruction;
import device.motherBoard.interfaces.ROMInterface;

public class ROMAdapter implements ROMInterface {
	
	// Association
	private ROMInterface rom;
	
	@Override public ECUInstruction getBIOS() {return this.rom.getBIOS();}
	
	// Getter & Setter
	public void connectROM(ROMInterface rom) {this.rom=rom;}
}
