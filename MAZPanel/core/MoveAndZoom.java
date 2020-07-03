package core;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import constant.MAZConstant;
import force.SimpleMoveForce;

public class MoveAndZoom {

	// Attributes
	private int maxZoomLevel = MAZConstant.MaxZoomLevel, minZoomLevel = MAZConstant.MinZoomLevel;
	private float zoomFactor = MAZConstant.ZoomFactor;

	// Components
	private AffineTransform coordTransform;
	private SimpleMoveForce force;
	
	// Working Variables
	private double[] lastForce;
	private Point dragStartPoint, dragEndPoint;
	private int zoomLevel = 0;
	
	// Constructor
	public MoveAndZoom() {
		this.lastForce = new double[2];
		this.coordTransform = new AffineTransform();
		this.force = new SimpleMoveForce();
	}
	
	public void update() {
		double[] moveXY = this.force.getMoveXY();
		this.coordTransform.translate(moveXY[0], moveXY[1]);
	}
	
	public void zoomCamera(MouseWheelEvent e) {
		Point2D p1 = transformPoint(e.getPoint(), this.coordTransform);
		if (e.getWheelRotation() > 0 && this.zoomLevel< this.maxZoomLevel) {
			this.zoomLevel++; this.coordTransform.scale(1 / this.zoomFactor, 1 / this.zoomFactor);
		}else if(e.getWheelRotation() < 0&& this.zoomLevel > this.minZoomLevel){
			this.zoomLevel--; this.coordTransform.scale(this.zoomFactor, this.zoomFactor);
		}
		Point2D p2 = transformPoint(e.getPoint(), this.coordTransform);
		this.coordTransform.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	public void moveCamera(MouseEvent e) {
		this.dragEndPoint = e.getPoint();
		Point2D.Float dragStart = transformPoint(this.dragStartPoint, this.coordTransform);
		Point2D.Float dragEnd = transformPoint(this.dragEndPoint, this.coordTransform);
		this.coordTransform.translate(dragEnd.getX() - dragStart.getX(), dragEnd.getY() - dragStart.getY());
		this.lastForce[0] = dragEnd.getX() - dragStart.getX();
		this.lastForce[1] = dragEnd.getY() - dragStart.getY();
		this.dragStartPoint = this.dragEndPoint;
	}
	
	// Getter & Setter
	public AffineTransform getAT() {return this.coordTransform;}
	public int getZoomLevel() {return this.zoomLevel;}
	public void setDragStartPoint(Point p) {this.dragStartPoint = p;}
	public void resetCoordTranform() {this.coordTransform = new AffineTransform();}
	public void forceReset() {this.lastForce[0] = 0; this.lastForce[1] = 0; this.force.reset();}
	public void setForce() {this.force.setVelocity(this.lastForce[0], this.lastForce[1]);}
	
	// Static Method
	public static Point2D.Float transformPoint(Point2D p1, AffineTransform at)  {
		Point2D.Float p2 = new Point2D.Float();
		try {at.createInverse().transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
}