package paint;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class PaintTool {
	
	public static void paintImage(Graphics2D g, BufferedImage img, Rectangle2D area, boolean fitInArea) {
		AffineTransform at = new AffineTransform();
		if(fitInArea) {
			at.translate(area.getX(), area.getY());
			at.setToTranslation(area.getX(), area.getY());
			at.scale(area.getWidth()/img.getWidth(), area.getHeight()/img.getHeight());
		}else {at.translate(area.getX() + area.getWidth()/2 - img.getWidth()/2, area.getY() + area.getHeight()/2 - img.getHeight()/2);}
		g.drawImage(img, at, null);
	}
	
	public static void paintText(Graphics2D g, String text, Font font, Rectangle2D area) {
		GlyphVector gv = font.createGlyphVector(g.getFontRenderContext(), text);
		Shape textShape = gv.getOutline();
		Rectangle2D textShapeBound = textShape.getBounds2D();
		AffineTransform at = new AffineTransform();
		at.translate(area.getX() + area.getWidth()/2 - textShapeBound.getWidth()/2, area.getY() + area.getHeight()/2 - textShapeBound.getHeight()/2 - textShapeBound.getY());
		g.fill(at.createTransformedShape(textShape));
	}
	
	public static void paintLeftText(Graphics2D g, String text, Font font, Rectangle2D area) {
		GlyphVector gv = font.createGlyphVector(g.getFontRenderContext(), text);
		Shape textShape = gv.getOutline();
		Rectangle2D textShapeBound = textShape.getBounds2D();
		AffineTransform at = new AffineTransform();
		at.translate(area.getX() + area.getWidth()/20, area.getY() + area.getHeight()/2 - textShapeBound.getHeight()/2 - textShapeBound.getY());
		g.fill(at.createTransformedShape(textShape));
	}
	
	public static Vector<Point2D> getSameRPoints(float ctrX, float ctrY, float wh, int nPoints) {
		Vector<Point2D> points = new Vector<Point2D>();
		for (int i = 0; i < nPoints; i++) {
			double angle = (i * 360.0) / nPoints;
			points.add(new Point2D.Double(ctrX + wh * Math.cos(Math.toRadians(angle - 90)),  ctrY + wh * Math.sin(Math.toRadians(angle - 90))));
		}
		return points;
	}
}
