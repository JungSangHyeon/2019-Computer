package paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

public class NibblePainter {

	// Attribute
	private static final int GapRadio = 1, BlockWHRadio = 4;
	private static final int Long1 = BlockWHRadio * 4 + GapRadio * 3;
	private static final int Long2 = BlockWHRadio * 3 + GapRadio * 2;
	private static final int NibbleWH = BlockWHRadio * 5 + GapRadio * 6;
	
	public static void paintNibble(Graphics2D g2d, double x, double y, double wh, Color bgC, Color mainC) {
		g2d.setColor(bgC);
		g2d.fill(new Rectangle2D.Double(x, y, wh, wh));
		
		g2d.setColor(mainC);
		NibblePainter.blockPaint(g2d, x, y, wh);
	}
	
	public static void paintNibble(Graphics2D g2d, double x, double y, double wh, Color bgC, Paint mainC) {
		g2d.setColor(bgC);
		g2d.fill(new Rectangle2D.Double(x, y, wh, wh));
		
		g2d.setPaint(mainC);
		NibblePainter.blockPaint(g2d, x, y, wh);
	}
	
	public static void paintNibble(Graphics2D g2d, double x, double y, double wh, Color mainC) {
		g2d.setColor(mainC);
		NibblePainter.blockPaint(g2d, x, y, wh);
	}
	
	public static void paintNibble(Graphics2D g2d, Rectangle2D rect, double wh, Color mainC) {
		g2d.setColor(mainC);
		NibblePainter.blockPaint(g2d, rect.getX() + rect.getWidth()/2 - wh/2, rect.getY() + rect.getHeight()/2 - wh/2, wh);
	}
	
	public static void paintNibble(Graphics2D g2d, Rectangle2D rect, double wh, Color bgC, Color mainC) {
		g2d.setColor(bgC);
		g2d.fill(new Rectangle2D.Double(rect.getX() + rect.getWidth()/2 - wh/2, rect.getY() + rect.getHeight()/2 - wh/2, wh, wh));
		
		g2d.setColor(mainC);
		NibblePainter.blockPaint(g2d, rect.getX() + rect.getWidth()/2 - wh/2, rect.getY() + rect.getHeight()/2 - wh/2, wh);
	}
	
	public static void paintNibble(Graphics2D g2d, double x, double y, double wh, Paint mainC) {
		g2d.setPaint(mainC);
		NibblePainter.blockPaint(g2d, x, y, wh);
	}

	private static void blockPaint(Graphics2D g2d, double x, double y, double wh) {
		double radioOne = wh / NibbleWH;
		double gap = radioOne * GapRadio;
		double blockWH = radioOne * BlockWHRadio;
		double long1 = radioOne * Long1;
		double long2 = radioOne * Long2;
		g2d.fill(new Rectangle2D.Double(x + gap, y + gap, blockWH, long1));
		g2d.fill(new Rectangle2D.Double(x + gap, y + gap*2 + blockWH, long2, blockWH));
		g2d.fill(new Rectangle2D.Double(x + gap*2 + blockWH, y + gap, long1, blockWH));
		g2d.fill(new Rectangle2D.Double(x + gap*4 + blockWH*3, y + gap, blockWH, long2));
		g2d.fill(new Rectangle2D.Double(x + gap, y + + gap*5 + blockWH*4, long1, blockWH));
		g2d.fill(new Rectangle2D.Double(x + gap*2 + blockWH, y + gap*3 + blockWH*2, blockWH, long2));
		g2d.fill(new Rectangle2D.Double(x + gap*5 + blockWH*4, y + gap*2 + blockWH, blockWH, long1));
		g2d.fill(new Rectangle2D.Double(x + gap*3 + blockWH*2, y + gap*4 + blockWH*3, long2, blockWH));
	}
}
