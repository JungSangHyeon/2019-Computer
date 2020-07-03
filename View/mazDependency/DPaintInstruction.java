package mazDependency;

import java.awt.Graphics2D;
import java.util.Vector;

import constant.MAZConstant;
import constant.MAZConstant.eMode;
import device.aDevice.Device;
import interfaces.IPaintInstruction;

public class DPaintInstruction implements IPaintInstruction{

	// Association
	private Vector<Device> devices;
	
	// Constructor
	public DPaintInstruction(Vector<Device> devices) {this.devices=devices;}

	@Override
	public void paint(Graphics2D g) {
		if(MAZConstant.getNowMode() == eMode.WhiteMode) {for(Device device : this.devices) {device.whitePaint(g);}}
		else if(MAZConstant.getNowMode() == eMode.BlackMode) {for(Device device : this.devices) {device.xrayPaint(g);}}
	}
}
