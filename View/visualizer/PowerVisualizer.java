package visualizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import aVisualizer.DeviceVisualizerInterface;

public class PowerVisualizer implements DeviceVisualizerInterface{

	// Attribute
	private float x = 0, y = 1480, w = 200, h = 200;
	private int border = 5;
	private int rotateSpeed = 10;
	private int arcR = 160, arcNum = 6;
	private Rectangle2D rect, innerRect;
	private Vector<Shape> arcShapes;
	private Color myGreen = new Color(0, 220, 0);
	private int center = 40;
	private int[] panWidths = {60, 100, 140};
	
	// Working Variable
	private double angle = 0;
	
	// Constructor
	public PowerVisualizer() {
		this.rect = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
		this.innerRect = new Rectangle2D.Double(this.x + this.border, this.y + this.border, this.w - this.border*2, this.h - this.border*2);
		this.arcShapes = new Vector<Shape>();
		double angle = 360 / this.arcNum;
		for(int i =0; i < this.arcNum; i++) {
			this.arcShapes.add(new Arc2D.Double(this.x + this.w/2 - this.arcR/2, this.y + this.h/2 - this.arcR/2, this.arcR, this.arcR, angle*i, angle, Arc2D.PIE));
		}
	}
	
	@Override
	public void whitePaint(Graphics2D g) {
		this.paintPower(g, Color.GRAY.darker().darker(), Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.GRAY, Color.DARK_GRAY);
	}
	@Override
	public void xrayPaint(Graphics2D g) {
		this.paintPower(g, Color.black, this.myGreen, this.myGreen, this.myGreen.darker().darker(), Color.black);
	}
	
	private void paintPower(Graphics2D g, Color borderC, Color bgC, Color pan1C, Color pan2C, Color capC) {
		g.setColor(bgC);
		g.fill(this.rect);
		g.setColor(borderC);
		g.fill(this.innerRect);
		AffineTransform temp = g.getTransform();
		g.rotate(this.angle, this.x + this.w/2, this.y + this.h/2);
		this.angle+=Math.toRadians(this.rotateSpeed);
		if(this.angle>360) {this.angle=this.angle%360;}
		for(int i =0; i < this.arcShapes.size(); i++) {
			if(i%2==0) {g.setPaint(pan1C);}
			else {g.setColor(pan2C);}
			g.fill(this.arcShapes.get(i));
		}
		g.setTransform(temp);
		g.setColor(capC);
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.fill(new Ellipse2D.Double(this.x + this.w/2 - this.center/2, this.y + this.h/2 - this.center/2, this.center, this.center));
		for(int wh : this.panWidths) {g.draw(new Ellipse2D.Double(this.x + this.w/2 - wh/2, this.y + this.h/2 - wh/2, wh, wh));}
		g.draw(new Line2D.Double(this.x, this.y, this.x + this.w, this.y + this.h));
		g.draw(new Line2D.Double(this.x, this.y + this.h, this.x + this.w, this.y));
		g.setStroke(s);
	}
}
