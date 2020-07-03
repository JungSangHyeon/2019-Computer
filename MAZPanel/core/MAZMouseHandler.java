package core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import interfaces.IMouseEventHandleInstruction;
import timer.CoordinatePaintTimer;
import timer.ZoomPercentPaintTimer;

public class MAZMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener{

	// Association
	private IMouseEventHandleInstruction mouseEventHandleInstruction;
	
	// Components
	private MoveAndZoom maz;
	private CoordinatePaintTimer coordinatePaintTimer;
	private ZoomPercentPaintTimer zoomPercentPaintTimer;
	
	// Working Variable
	protected boolean cameraMoveOn;
	
	// Constructor
	public MAZMouseHandler() {
		this.maz = new MoveAndZoom();
		this.coordinatePaintTimer = new CoordinatePaintTimer();
		this.zoomPercentPaintTimer = new ZoomPercentPaintTimer();
		this.cameraMoveOn = false;
	}
	
	public void init(IMouseEventHandleInstruction mouseEventHandleInstruction) {this.mouseEventHandleInstruction=mouseEventHandleInstruction;}
	
	public void start() {
		this.coordinatePaintTimer.startTimer();
		this.zoomPercentPaintTimer.startTimer();
	}
	
	public void update() {this.maz.update();}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		this.maz.zoomCamera(e);
		this.coordinatePaintTimer.reset();
		this.zoomPercentPaintTimer.reset();
		this.mouseEventHandleInstruction.cMouseWheelMoved(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.cameraMoveOn) {this.maz.moveCamera(e); this.coordinatePaintTimer.reset();}
		this.mouseEventHandleInstruction.cMouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			this.maz.setDragStartPoint(e.getPoint());
			this.cameraMoveOn = true;
			this.coordinatePaintTimer.reset();
			this.coordinatePaintTimer.setNowMinus(false);
			this.maz.forceReset();
		}
		this.mouseEventHandleInstruction.cMousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			this.cameraMoveOn = false;
			this.coordinatePaintTimer.setNowMinus(true);
			this.maz.setForce();
		}
		this.mouseEventHandleInstruction.cMouseReleased(e);
	}
	
	// Getter & Setter
	public AffineTransform getCoordTransform() {return this.maz.getAT();}
	public int getCoordinatePaintTime() {return this.coordinatePaintTimer.getCoordinatePaintTime();}
	public int getZoomPercentPaintTime() {return this.zoomPercentPaintTimer.getZoomPercentPaintTime();}
	public int getZoomLevel() {return this.maz.getZoomLevel();}

	// Encapsulate Method
	@Override public void mouseMoved(MouseEvent e) {this.mouseEventHandleInstruction.cMouseMoved(e);}
	@Override public void mouseClicked(MouseEvent e) {this.mouseEventHandleInstruction.cMouseClicked(e);}
	@Override public void mouseEntered(MouseEvent e) {this.mouseEventHandleInstruction.cMouseEntered(e);}
	@Override public void mouseExited(MouseEvent e) {this.mouseEventHandleInstruction.cMouseExited(e);}
}
