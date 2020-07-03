package device.power;

import device.aDevice.Device;
import device.motherBoard.adapter.CPUAdapter;
import device.motherBoard.interfaces.PowerInterface;
import visualizer.PowerVisualizer;

public class Power extends Device implements PowerInterface{

	// Association
	private CPUAdapter cpuAdapter;
	
	// Constructor
	public Power() {this.setVisualizer(new PowerVisualizer());}
	
	@Override public void on() {this.cpuAdapter.run();}
	
	// Getter & Setter
	@Override public void connectCPUAdapter(CPUAdapter cpu) {this.cpuAdapter=cpu;}
}
