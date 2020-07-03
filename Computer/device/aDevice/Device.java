package device.aDevice;

import java.awt.Graphics2D;

import aVisualizer.DeviceVisualizerInterface;

public class Device {

	// Association
	private DeviceVisualizerInterface deviceVisualizer;
	
	public void whitePaint(Graphics2D g) {if(this.deviceVisualizer!=null) {this.deviceVisualizer.whitePaint(g);}}
	public void xrayPaint(Graphics2D g) {if(this.deviceVisualizer!=null) {this.deviceVisualizer.xrayPaint(g);}}
	
	// Getter & Setter
	public void setVisualizer(DeviceVisualizerInterface deviceVisualizer) {this.deviceVisualizer=deviceVisualizer;}
}
