package device.rom;

import device.aDevice.Device;
import device.cpu.cu.cuInstruction.ECUInstruction;
import device.motherBoard.interfaces.ROMInterface;
import visualizer.ROMVisualizer;

public class ROM extends Device implements ROMInterface{
	
	// Constructor
	public ROM() {this.setVisualizer(new ROMVisualizer());}
	
	// Getter & Setter
	@Override public ECUInstruction getBIOS() {return ECUInstruction.eBIOS;}
}
