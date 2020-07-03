package visualizer.monitorVisualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import paint.PaintTool;

public class ApplicationVisualizer {
	
	// Attributes
	double x, y;
	private int applicationBorder = 10;
	private double applicationWH = 100;
	private int exeID = 0;
	private Font font = new Font(null, Font.PLAIN, 15);
	private String name;
	private Rectangle2D s, file, nameRect;
	
	// Working Variable
	private boolean selected = false;
	
	public ApplicationVisualizer (String name, int id, int locationID, int monitorX, int monitorY, int monitorBorder) {
		this.name = name;
		this.exeID = id;
		this.x = monitorX + this.applicationBorder + monitorBorder;
		this.y = monitorY + this.applicationBorder + monitorBorder + (this.applicationWH + this.applicationBorder)* locationID;
		this.s = new Rectangle2D.Double(this.x, this.y, this.applicationWH, this.applicationWH);
		this.file = new Rectangle2D.Double(this.x + this.applicationWH*0.25, this.y + this.applicationWH*0.125, this.applicationWH*0.5, this.applicationWH*0.625);
		this.nameRect = new Rectangle2D.Double(this.x, this.y + this.applicationWH*0.75, this.applicationWH, this.applicationWH*0.25);
	}
	
	public void paint(Graphics2D g, Point2D mousePoint, Color fileC, Color selected) {
		if(this.s.contains(mousePoint)) {this.selected = true;} else {this.selected = false;}
		g.setColor(fileC);
		g.fill(this.file);
		PaintTool.paintText(g, this.name, this.font, this.nameRect);
		if(this.selected) {
			g.setColor(selected);
			g.fill(this.s);
			g.setColor(selected.brighter().brighter());
			g.draw(this.s);
		}
	}
	
	// Getter & Setter
	public boolean getSelected() {return this.selected;}
	public int getID() {return this.exeID;}
}
