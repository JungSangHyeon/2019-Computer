package visualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import aVisualizer.DeviceVisualizerInterface;
import paint.PaintTool;

public class ConsoleVisualizer implements DeviceVisualizerInterface{

	// Attribute
	private int x = 600 + 700, y = 350;
	private int consoleWidth = 370, consoleHeight = 500;
	private int fontSize = 15;
	private int border = 3;
	private int showLineNum = 32;
	private Color backGroundColor = Color.BLACK, myGreen = new Color(0,220,0);
	private Rectangle2D borderRect, innerRect, titleBarRect;
	
	// Association
	private Vector<String> strings;
	
	// Constructor
	public ConsoleVisualizer() {
		this.borderRect = new Rectangle2D.Double(this.x, this.y, this.consoleWidth, this.consoleHeight);
		this.innerRect = new Rectangle2D.Double(this.x + this.border, this.y + this.border, this.consoleWidth - this.border*2, this.consoleHeight - this.border*2);
		this.titleBarRect = new Rectangle2D.Double(this.x, this.y - this.titleBarHeight + this.border, this.consoleWidth, this.titleBarHeight);
	}
	int titleBarHeight = 20;
	int titleBarFontSize = 15;
	
	public void init(Vector<String> strings) {this.strings=strings;}
	
	@Override
	public void whitePaint(Graphics2D g) {
		this.paintBorder(g, Color.LIGHT_GRAY);
		this.paintText(g, Color.WHITE);
		this.paintTitleBar(g, Color.WHITE, Color.black);
	}

	@Override
	public void xrayPaint(Graphics2D g) {
		this.paintBorder(g, this.myGreen);
		this.paintText(g, this.myGreen);
		this.paintTitleBar(g, this.myGreen, Color.black);
	}
	
	private void paintTitleBar(Graphics2D g, Color titleBarColor, Color textColor) {
		g.setColor(titleBarColor);
		g.fill(this.titleBarRect);
		g.setColor(textColor);
		PaintTool.paintLeftText(g, "CLI", new Font(null, Font.PLAIN, this.titleBarFontSize), this.titleBarRect);
	}
	
	private void paintBorder(Graphics2D g, Color borderColor) {
		g.setColor(borderColor);
		g.fill(this.borderRect);
		g.setColor(this.backGroundColor);
		g.fill(this.innerRect);
	}
	
	private void paintText(Graphics2D g, Color textColor) {
		g.setColor(textColor);
		if(this.strings.size() > this.showLineNum) {
			for(int i = this.strings.size() - this.showLineNum; i<this.strings.size(); i++) {
				PaintTool.paintLeftText(g,  i +" ) "+ this.strings.get(i), new Font(null, Font.PLAIN, this.fontSize), new Rectangle2D.Double(this.x + this.border, this.y + this.fontSize + this.border, this.consoleWidth - this.border*2, this.fontSize*2*(i- (this.strings.size() - this.showLineNum))));
			}
		}else{
			for(int i = 0; i<this.strings.size(); i++) {
				PaintTool.paintLeftText(g, i+" ) "+this.strings.get(i), new Font(null, Font.PLAIN, this.fontSize), new Rectangle2D.Double(this.x + this.border, this.y + this.fontSize + this.border, this.consoleWidth - this.border*2, this.fontSize*2*i));
			}
		}
	}
}
