package visualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import aVisualizer.DeviceVisualizerInterface;
import paint.PaintTool;

public class IOCVisualizer implements DeviceVisualizerInterface{

	// Attributes
	private int x = 0, y = 1180, w = 200, h = 200;
	private int border = 5;
	private Rectangle2D rect, innerRect;
	private Font font = new Font(null, Font.PLAIN, 80);
	private Color myGreen = new Color(0, 220, 0);

	// Constructor
	public IOCVisualizer() {
		this.rect = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
		this.innerRect = new Rectangle2D.Double(this.x + this.border, this.y+ this.border, this.w-this.border*2, this.h-this.border*2);
	}
	
	@Override public void whitePaint(Graphics2D g) {this.paintIOC(g, Color.DARK_GRAY, Color.gray, Color.LIGHT_GRAY);}
	@Override public void xrayPaint(Graphics2D g) {this.paintIOC(g, Color.BLACK, this.myGreen, this.myGreen);}

	private void paintIOC(Graphics2D g, Color mainC, Color borderC, Color textC) {
		g.setColor(borderC);
		g.fill(this.rect);
		g.setColor(mainC);
		g.fill(this.innerRect);
		g.setColor(textC);
		PaintTool.paintText(g, "IOC", this.font, this.rect);
	}
}
