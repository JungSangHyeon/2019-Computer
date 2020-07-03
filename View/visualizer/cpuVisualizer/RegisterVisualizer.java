package visualizer.cpuVisualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import device.cpu.register.Register;
import paint.PaintTool;

public class RegisterVisualizer {

	// Attribute
	private String name;
	private int gapXNum, gapYNum;
	private Color blockColor, textColor;
	private Font font;
	
	// Association
	private Register register;
	
	// Constructor
	public RegisterVisualizer(Register register, String name, int gapXNum, int gapYNum, Color blockColor, Color textColor, Font font) {
		this.name=name;
		this.register=register;
		this.gapXNum=gapXNum;
		this.gapYNum=gapYNum;
		this.blockColor = blockColor;
		this.textColor = textColor;
		this.font = font;
	}
	
	public void paint(Graphics2D g, double x, double y, double gapX, double gapY, double w, double h) {
		Rectangle2D blockRect = new Rectangle2D.Double(x + gapX * this.gapXNum, y + gapY * this.gapYNum, w, h);
		Rectangle2D nameRect = new Rectangle2D.Double(x + gapX * this.gapXNum, y + gapY * this.gapYNum, w, h/3);
		
		g.setColor(this.blockColor);
		g.fill(blockRect);
		
		g.setColor(this.textColor);
		PaintTool.paintText(g, this.name, this.font, nameRect); 
		PaintTool.paintText(g, this.register.getData()+"", this.font, blockRect); 
	}
}
