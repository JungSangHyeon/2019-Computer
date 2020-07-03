package visualizer.monitorVisualizer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import aVisualizer.DeviceVisualizerInterface;
import constant.Constant;
import core.MoveAndZoom;
import device.hdd.HDD.EExe;
import paint.NibblePainter;

public class MonitorVisualizer implements DeviceVisualizerInterface{

	// Attribute
	private int border = 5;
	private int x = - border, y = - border;
	private int nibbleWH = 400;
	private Color myGreen = new Color(0,220,0), xRaySelectedColor = new Color(0, 150, 0, 100), whiteSelectedColor = new Color(100,100,100,100);
	private Vector<ApplicationVisualizer> applicationVisualizers;
	private Rectangle2D borderRect, innerRect;
	
	// Working Variable
	private int mouseX, mouseY;
	
	// Constructor
	public MonitorVisualizer() {
		this.applicationVisualizers = new Vector<ApplicationVisualizer>();
		this.borderRect = new Rectangle2D.Double(this.x, this.y, Constant.ScreenWidth + this.border*2, Constant.ScreenHeight+ this.border*2);
		this.innerRect = new Rectangle2D.Double(this.x + this.border, this.y + this.border, Constant.ScreenWidth + this.border*2- this.border*2, Constant.ScreenHeight + this.border*2- this.border*2);
		for(int i =0; i<EExe.values().length; i++) {
			String exeName = EExe.values()[i].getName();
			if(!exeName.equals("")) {this.applicationVisualizers.add(new ApplicationVisualizer(exeName, i, this.applicationVisualizers.size(), this.x, this.y, this.border));}
		}
	}
	
	@Override public void whitePaint(Graphics2D g) {paintMonitor(g, Color.LIGHT_GRAY, Color.BLACK, Color.white, this.whiteSelectedColor, Color.WHITE, Color.black, Color.BLACK, Color.white);}
	@Override public void xrayPaint(Graphics2D g) {paintMonitor(g, this.myGreen, Color.black, this.myGreen, this.xRaySelectedColor, this.myGreen, Color.black, Color.black, this.myGreen);}
	
	private void paintMonitor(Graphics2D g, Color borderC, Color bgC, Color appNormalC, Color appSelectC, Color mouseC, Color mouseBorderC, Color nibbleC, Color nibbleBGC) {
		// Border
		g.setColor(borderC);
		g.fill(this.borderRect);
		
		// Inside
		g.setColor(bgC);
		g.fill(this.innerRect);
		
		NibblePainter.paintNibble(g, this.innerRect, this.nibbleWH, nibbleBGC, nibbleC);
		
		// Application
		Point2D translatedMouse = MoveAndZoom.transformPoint(new Point2D.Double(this.mouseX, this.mouseY), g.getTransform());
		for(ApplicationVisualizer av : this.applicationVisualizers) {av.paint(g, translatedMouse, appNormalC, appSelectC);}
	}

	// Getter & Setter
	public void setMouseXY(int mouseX, int mouseY) {this.mouseX=mouseX; this.mouseY=mouseY;}
	public int getExecutedExeID() {for(ApplicationVisualizer av : this.applicationVisualizers) {if(av.getSelected()) {return av.getID();}}return -1;}
}
