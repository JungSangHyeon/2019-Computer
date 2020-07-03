package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import constant.MAZConstant;
import interfaces.IPaintInstruction;
import paint.PaintTool;

public class MAZPanelPaint {

	// Attribute
	private int lineInterval = MAZConstant.LineInterval;
	private int lineAlpaLimit = MAZConstant.LineAlpaLimit;
	private int zoomPercentGranulity = MAZConstant.ZoomPercentGranulity;
	private int zoomPercentTextSize = MAZConstant.ZoomPercentTextSize;
	private int zoomBlockFixedAlpaAreaValue = MAZConstant.ZoomBlockFixedAlpaAreaValue;
	private int zoomBlockFixedAlpaValue = MAZConstant.ZoomBlockFixedAlpaValue;
	private int zoomBlockWidth = MAZConstant.BlockWidth;
	private int zoomBlockheight = MAZConstant.Blockheight;
	private int screenWidth, screenHeight;
	
	// Associate
	private IPaintInstruction paintInstruction;
	
	// Working Variable
	private int coordinateOnTime, zoomPercentOnTime, zoomLevel;
	
	public void init(IPaintInstruction paintInstruction, int screenWidth, int screenHeight) {
		this.paintInstruction=paintInstruction;
		this.screenWidth=screenWidth;
		this.screenHeight=screenHeight;
	}
	
	public void paint(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Paint Coordinate Lines
		if(this.coordinateOnTime > 0) { // Mask
			double radio = (double)this.coordinateOnTime / (double)MAZConstant.CoordinatePaintTime;
			int alpa = ((int) (radio * 255) > this.lineAlpaLimit) ? this.lineAlpaLimit : (int) (radio * 255);
			g.setColor(new Color(MAZConstant.CoordColor.getRed(), MAZConstant.CoordColor.getGreen(), MAZConstant.CoordColor.getBlue(), alpa));
			Point2D.Float start = MoveAndZoom.transformPoint(new Point(0, 0), g.getTransform());
			Point2D.Float end =  MoveAndZoom.transformPoint(new Point(this.screenWidth, this.screenHeight), g.getTransform());
			int aCoordWidth = (int) (end.x - start.x) + this.lineInterval*2; 
			int aCoordHeight = (int) (end.y - start.y) + this.lineInterval *2;
			int transX = (int)(g.getTransform().getTranslateX()/g.getTransform().getScaleX() % this.lineInterval);
			int transY = (int)(g.getTransform().getTranslateY()/g.getTransform().getScaleY() % this.lineInterval);
			for(int i=-1; i< aCoordWidth +1; i+=this.lineInterval) {g.fill(new Rectangle((int)start.x - this.lineInterval + i - MAZConstant.LineWidth/2 + transX, (int)start.y + transY - this.lineInterval, MAZConstant.LineWidth, aCoordHeight));}
			for(int i=-1; i< aCoordHeight +1; i+=this.lineInterval) {g.fill(new Rectangle((int)start.x + transX - this.lineInterval, (int)start.y - this.lineInterval + i - MAZConstant.LineWidth/2 + transY, aCoordWidth, MAZConstant.LineWidth));}
		}
		
		// Paint Dependency
		this.paintInstruction.paint(g);
		
		// Paint Zoom Percent
		if(this.zoomPercentOnTime > 0) {
			double zoomPaintRadio = (double)this.zoomPercentOnTime / (double)MAZConstant.ZoomPercentPaintTime;
			int zAlpa = (int) (zoomPaintRadio * 255);
			g.setTransform(new AffineTransform());
			if(zAlpa > this.zoomBlockFixedAlpaAreaValue) {zAlpa = this.zoomBlockFixedAlpaValue;} else {zAlpa = 255 - (100 - zAlpa) * 4;}
			if(zAlpa<0) {zAlpa=0;}
			
			// Block Paint
			g.setColor(new Color(MAZConstant.ZoomBlockColor.getRed(), MAZConstant.ZoomBlockColor.getGreen(), MAZConstant.ZoomBlockColor.getBlue(),  zAlpa));
			g.fill(new Rectangle(this.screenWidth/2  - this.zoomBlockWidth/2, this.screenHeight/2 - this.zoomBlockheight/2, this.zoomBlockWidth, this.zoomBlockheight));
			
			// Text Paint
			g.setColor(new Color(MAZConstant.ZoomBlockTextColor.getRed(), MAZConstant.ZoomBlockTextColor.getGreen(), MAZConstant.ZoomBlockTextColor.getBlue(), zAlpa));
			double percent = 100 * Math.pow(MAZConstant.ZoomFactor, -this.zoomLevel);
			PaintTool.paintText(
					g, 
					(int) percent / this.zoomPercentGranulity * this.zoomPercentGranulity + "%", 
					new Font(null, Font.PLAIN, this.zoomPercentTextSize), 
					new Rectangle(this.screenWidth/2 - this.zoomBlockWidth/2, this.screenHeight/2 - this.zoomBlockheight/2, this.zoomBlockWidth, this.zoomBlockheight)
			);
		}
	}
	
	// Getter & Setter
	public void setCoordinateOnTime(int i) {this.coordinateOnTime=i;}
	public void setZoomPercentOnTime(int i) {this.zoomPercentOnTime=i;}
	public void setZoomLevel(int zoomLevel) {this.zoomLevel=zoomLevel;}
}
