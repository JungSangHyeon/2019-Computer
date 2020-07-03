package dependency;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import interfaces.IPaintInstruction;
import paint.PaintTool;
import paint.RainbowPaint;

public class DTESTPaintInstruction implements IPaintInstruction{

	// Attributes
	private Rectangle area = new Rectangle(100,100,300,100);
	private String text = "Test Block";
	private Font font = new Font(null, Font.BOLD, 50);
	private RainbowPaint rp = new RainbowPaint();
	
	@Override
	public void paint(Graphics2D g) {
		g.setPaint(rp.getRainbow(this.area.width));
		g.fill(this.area);
		
		g.setColor(Color.WHITE);
		PaintTool.paintText(g, this.text, this.font, this.area);
	}
}
