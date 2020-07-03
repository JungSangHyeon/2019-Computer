package visualizer.zETCVisualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import paint.PaintTool;

public class RectVisualizer {

	// Attribute
	private String name;
	private Color blockColor, textColor;
	private Font font;
	
	// Constructor
	public RectVisualizer(String name,Color blockColor, Color textColor, Font font) {
		this.name=name;
		this.blockColor = blockColor;
		this.textColor = textColor;
		this.font = font;
	}
	
	public void paint(Graphics2D g, double x, double y, double w, double h) {
		Rectangle2D blockRect = new Rectangle2D.Double(x, y, w, h);
		
		g.setColor(this.blockColor);
		g.fill(blockRect);
		
		g.setColor(this.textColor);
		PaintTool.paintText(g, this.name, this.font, blockRect);
	}
}
