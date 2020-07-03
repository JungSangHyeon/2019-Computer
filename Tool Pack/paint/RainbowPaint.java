package paint;

import java.awt.Color;
import java.awt.GradientPaint;

public class RainbowPaint {

	// Global
	private static final float HUE_MIN = 0, HUE_MAX = 1, Delta = 0.05f;
	
	// Working Variable
	private float hue = RainbowPaint.HUE_MIN;
	private Color color1 = Color.white, color2 = Color.black;
	
	public GradientPaint getRainbow(float w) {
		this.hue += Delta;
		if (this.hue > HUE_MAX) {this.hue = HUE_MIN;}
		this.color1 = Color.getHSBColor(this.hue, 1, 1);
		this.color2 = Color.getHSBColor(this.hue + 16 * Delta, 1, 1);
		return new GradientPaint(0, 0, this.color1, w, 0, this.color2);
	}
}
