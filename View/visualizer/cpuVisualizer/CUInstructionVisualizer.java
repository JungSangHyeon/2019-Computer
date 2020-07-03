package visualizer.cpuVisualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import device.cpu.cu.cuInstruction.ECUInstruction;
import paint.PaintTool;

public class CUInstructionVisualizer {

	// Attribute
	private String name;
	private Color blockColor, textColor;
	private Font font;
	
	// Constructor
	public CUInstructionVisualizer(String name,Color blockColor, Color textColor, Font font) {
		this.name=name;
		this.blockColor = blockColor;
		this.textColor = textColor;
		this.font = font;
	}
	
	public void paint(Graphics2D g, double x, double y, double w, double h, int instruction) {
		Rectangle2D blockRect = new Rectangle2D.Double(x, y, w, h);
		Rectangle2D codeRect = new Rectangle2D.Double(x, y, w/2, h);
		Rectangle2D parameterRect = new Rectangle2D.Double(x + w/2, y, w/2, h);
		Rectangle2D nameRect = new Rectangle2D.Double(x, y, w, h/3);
		
		int code = instruction>>>16;
		int parameter = instruction & 0x0000ffff;
		
		g.setColor(this.blockColor);
		g.fill(blockRect);
		
		g.setColor(this.textColor);
		PaintTool.paintText(g, this.name, this.font, nameRect);
		PaintTool.paintText(g, ECUInstruction.values()[code].name(), this.font, codeRect); 
		PaintTool.paintText(g, ""+parameter, this.font, parameterRect); 
	}
}
