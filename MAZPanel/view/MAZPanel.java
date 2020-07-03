package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import constant.MAZConstant;
import core.MAZMouseHandler;
import core.MAZPanelPaint;
import event.KeyDispatcher;
import event.RepaintTimer;
import interfaces.IKeyEventHandleInstruction;
import interfaces.IMouseEventHandleInstruction;
import interfaces.IPaintInstruction;

@SuppressWarnings("serial")
public class MAZPanel extends JPanel{

	// Attributes
	private Color backGroundColor = MAZConstant.PanelColor;
	private int repaintInterval = MAZConstant.PanelRepaintInterval;

	// Association
	private IKeyEventHandleInstruction keyEventHandleInstruction; 
	private IMouseEventHandleInstruction mouseEventHandleInstruction; 
	private IPaintInstruction paintInstruction;
		
	// Components
	private RepaintTimer repaintTimer;
	private MAZPanelPaint mazPanelPaint;
	private MAZMouseHandler mazMouseHandler;
	private KeyDispatcher keyDispatcher;
	
	// Constructor
	public MAZPanel() {
		// Set Attributes
		this.setBackground(this.backGroundColor);
		
		// Create Components
		this.repaintTimer = new RepaintTimer(this.repaintInterval, this);
		this.mazPanelPaint = new MAZPanelPaint();
		this.mazMouseHandler = new MAZMouseHandler();
		this.keyDispatcher = new KeyDispatcher();
		
		// Add Listener
		this.addMouseListener(this.mazMouseHandler);
		this.addMouseWheelListener(this.mazMouseHandler);
		this.addMouseMotionListener(this.mazMouseHandler);
	}

	public void init() {
		this.keyDispatcher.init(this.keyEventHandleInstruction);
		this.mazMouseHandler.init(this.mouseEventHandleInstruction);
		this.mazPanelPaint.init(this.paintInstruction, this.getWidth(), this.getHeight());
	}

	public void start() {
		this.repaintTimer.startTimer();
		this.mazMouseHandler.start();
	}
	
	@Override 
	public void paint(Graphics g) {
		this.mazMouseHandler.update();
		this.setBackground(MAZConstant.BackgroundColor);
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setTransform(this.mazMouseHandler.getCoordTransform());
		this.mazPanelPaint.setCoordinateOnTime(this.mazMouseHandler.getCoordinatePaintTime());
		this.mazPanelPaint.setZoomPercentOnTime(this.mazMouseHandler.getZoomPercentPaintTime());
		this.mazPanelPaint.setZoomLevel(this.mazMouseHandler.getZoomLevel());
		this.mazPanelPaint.paint(g2d);
	}
	
	// Getter & Setter
	public void setKeyEventHandleInstruction(IKeyEventHandleInstruction keyEventHandleInstruction) {this.keyEventHandleInstruction=keyEventHandleInstruction;}
	public void setMouseEventHandleInstruction(IMouseEventHandleInstruction mouseEventHandleInstruction) {this.mouseEventHandleInstruction=mouseEventHandleInstruction;}
	public void setPaintInstruction(IPaintInstruction paintInstruction) {this.paintInstruction=paintInstruction;}
}
