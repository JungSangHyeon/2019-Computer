package visualizer.cpuVisualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import device.cpu.register.Register;
import device.cpu.status.StatusControl;
import device.cpu.status.StatusControl.EStatusFlag;
import paint.PaintTool;

public class StatusVisaulizer {

	// Attribute
	private String name;
	private Color blockColor, textColor;
	private Font registerFont, flagFont;
	
	// Association
	private Register status;
	
	// Constructor
	public StatusVisaulizer(Register status, String name, Color blockColor, Color textColor,  Font registerFont, Font flagFont) {
		this.name=name;
		this.status=status;
		this.blockColor = blockColor;
		this.textColor = textColor;
		this.registerFont = registerFont;
		this.flagFont = flagFont;
	}
	
	public void paint(Graphics2D g, double x, double y, double w, double h) {
		Rectangle2D blockRect = new Rectangle2D.Double(x, y, w, h);
		Rectangle2D nameRect = new Rectangle2D.Double(x, y, w, h/3);
		
		g.setColor(this.blockColor);
		g.fill(blockRect);
		
		g.setColor(this.textColor);
		PaintTool.paintText(g, this.name, this.registerFont, nameRect); 

		int flagNum = EStatusFlag.values().length;
		for(int i = 0; i<flagNum; i++) {
			EStatusFlag flag = EStatusFlag.values()[i];
			PaintTool.paintText(g, flag.name() + ", " + StatusControl.getStatusValue(flag, this.status.getData()), this.flagFont, new Rectangle2D.Double(x, y, w, h/3 + h/3*2 / flagNum*(i+2)));
		}
	}
}
