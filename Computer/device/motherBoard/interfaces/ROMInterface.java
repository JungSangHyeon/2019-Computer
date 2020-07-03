package device.motherBoard.interfaces;

import device.cpu.cu.cuInstruction.ECUInstruction;

public interface ROMInterface {
	ECUInstruction getBIOS();
}
