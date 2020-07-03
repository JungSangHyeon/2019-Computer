package visualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import aVisualizer.DeviceVisualizerInterface;
import device.hdd.HDD.EExe;
import paint.PaintTool;

public class HDDVisualizer implements DeviceVisualizerInterface{

	// Attribute 
	private int x = 0, y = 1780, w = 200, h = 250;
	private int diskWH = 160;
	private Rectangle2D rect, textRect, innerRect;
	private Ellipse2D disk;
	private double angle = 0;
	private Vector<Ellipse2D> dots;
	private int dotWH = 20, dotDistance = 40, dotNum = 7, dotRotateSpeed = 10;
	private Font whiteFont = new Font(null, Font.PLAIN, 60), xRayFont = new Font(null, Font.PLAIN, 10);
	private int exeNameGap = 15;
	private int border = 5;
	private Color myGreen = new Color(0,220,0);
	
	// Association
	private EExe[] exes;
	
	// Constructor
	public HDDVisualizer(EExe[] exes) {
		this.exes=exes;
		this.rect = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
		this.innerRect = new Rectangle2D.Double(this.x + this.border, this.y + this.border, this.w - this.border*2, this.h - this.border*2);
		this.textRect = new Rectangle2D.Double(this.x, this.y + this.h/30 + this.diskWH, this.w, this.h - this.diskWH);
		this.disk = new Ellipse2D.Double(this.x + this.w/2 - this.diskWH/2, this.y + this.h/5*2 - this.diskWH/2, this.diskWH, this.diskWH);
		this.dots = new  Vector<Ellipse2D>();
		for(Point2D p : PaintTool.getSameRPoints(this.x + this.w/2, this.y + this.h/5*2, this.dotDistance, this.dotNum)) {
			this.dots.add(new Ellipse2D.Double(p.getX()-this.dotWH/2, p.getY()-this.dotWH/2, this.dotWH, this.dotWH));
		}
	}

	@Override
	public void whitePaint(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fill(this.rect);
		
		g.setColor(Color.LIGHT_GRAY);
		g.fill(this.disk);
		
		AffineTransform temp = g.getTransform();
		g.rotate(this.angle, this.x + this.w/2, this.y + this.h/5*2);
		this.angle+=Math.toRadians(this.dotRotateSpeed);
		if(this.angle>360) {this.angle=this.angle%360;}
		g.setColor(Color.gray);
		for(Ellipse2D dot : this.dots) {g.fill(dot);}
		g.setTransform(temp);
		
		g.setColor(Color.white);
		PaintTool.paintText(g, "HDD", this.whiteFont, this.textRect);
	}

	@Override
	public void xrayPaint(Graphics2D g) {
		g.setColor(this.myGreen);
		g.fill(this.rect);
		
		g.setColor(Color.BLACK);
		g.fill(this.innerRect);
		
		g.setColor(this.myGreen);
		int i = 1;
		for(EExe exe : this.exes) {
			if(!exe.getName().equals("")) {
				PaintTool.paintLeftText(g, exe.getName(), this.xRayFont, new Rectangle2D.Double(this.x, this.y+this.exeNameGap*i++, this.w, this.exeNameGap));
			}
		}
	}
}
