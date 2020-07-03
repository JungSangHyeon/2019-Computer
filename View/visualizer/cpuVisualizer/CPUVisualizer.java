package visualizer.cpuVisualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import aVisualizer.DeviceVisualizerInterface;
import device.cpu.register.Register;
import paint.NibblePainter;
import visualizer.zETCVisualizer.RectVisualizer;

public class CPUVisualizer implements DeviceVisualizerInterface{

	// Attribute
	private double cpuWH = 200;
	private double wGapRadio = 0.02, hGapRadio = 0.02;
	private double registerWidthRadio = 0.307, registerHeightRadio = 0.176;
	private double statusTextSizeRadio = 0.025;
	private double registerTextSizeRadio = 0.05;
	private double bigTextSizeRadio = 0.1;
	private Color blockColor = Color.BLACK, textColor = new Color(0, 220, 0);
	private float x = 300, y = 1480;
	
	// Working Variable
	private double wGap,hGap, registerWidth, registerHeight;
	
	// Association
	private Register ir, pc, cp, sp, mar, mbr, ac, status, intMaster;
	
	// Component
	private Font statusFont, registerFont, bigFont;
	private RegisterVisualizer pcVisualizer,  cpVisualizer, spVisualizer, marVisualizer, mbrVisualizer, acVisualizer, intMasterVisualizer;
	private RegisterVisualizer[] visualizers;
	private CUInstructionVisualizer irVisualizer;
	private RectVisualizer cuVisualizer, aluVisualizer;
	private StatusVisaulizer statusVisualizer;
	
	// Constructor
	public CPUVisualizer() {
		// Set Attribute
		this.wGap = this.cpuWH * this.wGapRadio;
		this.hGap = this.cpuWH * this.hGapRadio;
		this.registerWidth = this.cpuWH * this.registerWidthRadio;
		this.registerHeight = this.cpuWH * this.registerHeightRadio;
		this.statusFont = new Font(null, Font.PLAIN, (int) (this.cpuWH * this.statusTextSizeRadio));
		this.registerFont = new Font(null, Font.PLAIN, (int) (this.cpuWH * this.registerTextSizeRadio));
		this.bigFont = new Font(null, Font.PLAIN, (int) (this.cpuWH * this.bigTextSizeRadio));
		
		// Create Component
		this.irVisualizer = new CUInstructionVisualizer("ir", this.blockColor, this.textColor, this.registerFont);
		this.cuVisualizer = new RectVisualizer("CU", this.blockColor, this.textColor, this.bigFont);
		this.aluVisualizer = new RectVisualizer("ALU", this.blockColor, this.textColor, this.bigFont);
	}
	
	public void init(Register ir, Register pc, Register cp, Register sp, Register mar, Register mbr, Register ac, Register status, Register intMaster) {
		this.ir = ir; this.pc = pc; this.cp = cp; this.sp = sp; this.mar = mar; this.mbr = mbr; this.ac = ac; this.status = status; this.intMaster = intMaster;
		this.statusVisualizer = new StatusVisaulizer(this.status, "status", this.blockColor, this.textColor, this.registerFont, this.statusFont);
		this.pcVisualizer = new RegisterVisualizer(this.pc, "pc", 0, 0, this.blockColor, this.textColor, this.registerFont);
		this.cpVisualizer = new RegisterVisualizer(this.cp, "cp", 1, 0, this.blockColor, this.textColor, this.registerFont);
		this.spVisualizer = new RegisterVisualizer(this.sp, "sp", 2, 0, this.blockColor, this.textColor, this.registerFont);
		this.marVisualizer = new RegisterVisualizer(this.mar, "mar", 0, 1, this.blockColor, this.textColor, this.registerFont);
		this.mbrVisualizer = new RegisterVisualizer(this.mbr, "mbr", 0, 2, this.blockColor, this.textColor, this.registerFont);
		this.intMasterVisualizer = new RegisterVisualizer(this.intMaster, "int master", 1, 4, this.blockColor, this.textColor, this.registerFont);
		this.acVisualizer = new RegisterVisualizer(this.ac, "ac", 2, 4, this.blockColor, this.textColor, this.registerFont);
		RegisterVisualizer[] visualizers = {this.pcVisualizer, this.cpVisualizer, this.spVisualizer, this.marVisualizer, this.mbrVisualizer, this.acVisualizer, this.intMasterVisualizer};
		this.visualizers=visualizers;
	}
	
	@Override public void whitePaint(Graphics2D g) {NibblePainter.paintNibble(g, this.x, this.y, this.cpuWH, Color.WHITE, Color.BLACK);}
	@Override
	public void xrayPaint(Graphics2D g) {
		g.setColor(this.textColor);
		g.fill(new Rectangle2D.Double(this.x, this.y, this.cpuWH, this.cpuWH));
		double startX = this.x+this.wGap, startY = this.y+this.hGap, registerWGap = this.wGap + this.registerWidth, registerHGap = this.hGap + this.registerHeight;
		for(RegisterVisualizer registerVisualizer : visualizers) {registerVisualizer.paint(g, startX, startY, registerWGap, registerHGap, this.registerWidth, this.registerHeight);}
		this.irVisualizer.paint(g, startX + registerWGap, startY + registerHGap, this.registerWidth * 2 + this.wGap, this.registerHeight, this.ir.getData());
		this.cuVisualizer.paint(g, startX + registerWGap, startY + registerHGap * 2, this.registerWidth, this.registerHeight*2 + this.hGap);
		this.aluVisualizer.paint(g, startX + registerWGap*2, startY + registerHGap * 2, this.registerWidth, this.registerHeight*2 + this.hGap);
		this.statusVisualizer.paint(g, startX, startY + registerHGap*3, this.registerWidth, this.registerHeight*2 + this.hGap);
	}
}
