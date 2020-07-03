package visualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

import aVisualizer.DeviceVisualizerInterface;
import constant.Constant;
import device.cpu.cu.cuInstruction.ECUInstruction;
import paint.NibblePainter;
import paint.PaintTool;
import paint.RainbowPaint;

public class MemoryVisualizer implements DeviceVisualizerInterface{
	
	// Attribute
	private int border = 5;
	private int instructionWidth = 120, instructionHeight = 10;
	private int wInstructionNum = 7, hInstructionNum = Constant.processSize;
	private float x = 600, y;
	private Font font;
	private int w, h, n;
	private Color myGreen = new Color(0,220,0);
	
	// Working Variable
	private int nowUse = -1;
		
	// Associate
	private int[] realMemory;
	
	// Component
	private RainbowPaint rainbowPaint;
	
	// Constructor
	public MemoryVisualizer(int[] realMemory, int y, int n) {
		this.y = y; this.n = n;
		this.realMemory = realMemory;
		this.font = new Font(null, Font.PLAIN, this.instructionHeight);
		this.w = this.instructionWidth * this.wInstructionNum + this.border * 2;
		this.h = this.instructionHeight * this.hInstructionNum + this.border * 2;
		this.rainbowPaint = new RainbowPaint();
	}
	
	@Override
	public void whitePaint(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fill(new Rectangle2D.Double(this.x, this.y, this.w, this.h));
		
		g.setColor(Color.DARK_GRAY.darker().darker().darker());
		g.fill(new Rectangle2D.Double(this.x + this.border, this.y + this.border, this.w - this.border*2, this.h - this.border*2));
		
		Paint p = this.rainbowPaint.getRainbow(this.w);
		g.setPaint(p);
		NibblePainter.paintNibble(g, this.x+440, this.y+158, 94, p);
		PaintTool.paintText(g, "MEM   RY", new Font(null, Font.BOLD, 120), new Rectangle2D.Double(this.x, this.y, this.w, this.h));
	}

	@Override
	public void xrayPaint(Graphics2D g) {
		g.setColor(this.myGreen);
		g.fill(new Rectangle2D.Double(this.x, this.y, this.w, this.h));
		
		g.setColor(Color.BLACK);
		g.fill(new Rectangle2D.Double(this.x + this.border, this.y + this.border, this.w - this.border*2, this.h - this.border*2));
		
		g.setColor(this.myGreen);
		for(int w = 0; w < this.wInstructionNum; w++) {for(int h = 0; h < this.hInstructionNum; h++) {paintInstruction(g, w, h);}}
	}

	private void paintInstruction(Graphics2D g, int wNum, int hNum) {
		int index = wNum*this.hInstructionNum + hNum + (this.n-1)*this.hInstructionNum*this.wInstructionNum;
		int instruction = this.realMemory[wNum*this.hInstructionNum + hNum];
		int code = instruction>>>16;
		int parameter = instruction & 0x0000ffff;
		Rectangle2D instructionBlock = new Rectangle2D.Double(this.x + this.instructionWidth*wNum + this.border, this.y+ this.instructionHeight*hNum + this.border, this.instructionWidth, this.instructionHeight);
		if(code != 0 || parameter != 0) {
			String instructionString = "<"+String.format("%03d", index) + ">  "+ECUInstruction.values()[code].name();
			String parameterStirng = String.format("%20d", parameter);
			if(index == this.nowUse + (this.n-1)*this.hInstructionNum*this.wInstructionNum) {
				g.setColor(this.myGreen);
				g.fill(instructionBlock);
				g.setColor(Color.BLACK);
				PaintTool.paintLeftText(g, instructionString, this.font, instructionBlock);
				PaintTool.paintText(g, parameterStirng, this.font, instructionBlock);
				g.setColor(this.myGreen);
			}else {
				PaintTool.paintLeftText(g, instructionString, this.font, instructionBlock);
				PaintTool.paintText(g, parameterStirng, this.font, instructionBlock);
			}
		}
	}

	public void setNowUse(int i) {this.nowUse = i;}
}
