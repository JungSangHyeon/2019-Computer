package device.motherBoard.adapter;

import device.motherBoard.interfaces.PowerInterface;

public class PowerAdapter implements PowerInterface{

	// Association
	private CPUAdapter cpu;
	private PowerInterface power;
	
	public void init() {this.power.connectCPUAdapter(this.cpu);}
	@Override public void on() {/*진짜 Power가 한다*/}

	// Getter & Setter
	public void connectPower(PowerInterface power) {this.power=power;}
	@Override public void connectCPUAdapter(CPUAdapter cpu) {this.cpu=cpu;}
}
