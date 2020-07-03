package visualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import aVisualizer.DeviceVisualizerInterface;
import paint.PaintTool;

public class ROMVisualizer implements DeviceVisualizerInterface{

	// Attribute
	private float x = 300, y = 1780;
	private int w = 200, h = 150;
	private Rectangle2D bodyRect, innerRect;
	private Vector<Rectangle2D> legShapes;
	private Font font;
	private int border = 5;
	private int oneSideLegNum = 6, legW = 10, legH = 16;
	private Color myGreen = new Color(0, 220, 0);
	
	// Constructor
	public ROMVisualizer() {
		this.bodyRect = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
		this.innerRect = new Rectangle2D.Double(this.x + this.border, this.y + this.border, this.w - this.border*2, this.h - this.border*2);
		this.font = new Font(null, Font.PLAIN, 50);
		this.legShapes = new Vector<Rectangle2D>();
		for(int i = 0; i<this.oneSideLegNum; i++) {
			this.legShapes.add(new Rectangle2D.Double(this.x + this.w/(this.oneSideLegNum+1)*(i+1) - this.legW/2, this.y - this.legH/2, this.legW, this.legH));
			this.legShapes.add(new Rectangle2D.Double(this.x + this.w/(this.oneSideLegNum+1)*(i+1) - this.legW/2, this.y + this.h - this.legH/2, this.legW, this.legH));
		}
	}
	
	@Override
	public void whitePaint(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fill(this.bodyRect);
		g.setColor(Color.LIGHT_GRAY);
		PaintTool.paintText(g, "ROM", this.font, this.bodyRect);
		for(Rectangle2D legShape : this.legShapes) {g.fill(legShape);}
	}

	@Override
	public void xrayPaint(Graphics2D g) {
		g.setColor(this.myGreen);
		g.fill(this.bodyRect);
		g.setColor(Color.BLACK);
		g.fill(this.innerRect);
		g.setColor(this.myGreen);
		PaintTool.paintText(g, "BIOS", this.font, this.bodyRect);
		for(Rectangle2D legShape : this.legShapes) {g.fill(legShape);}
	}
}
