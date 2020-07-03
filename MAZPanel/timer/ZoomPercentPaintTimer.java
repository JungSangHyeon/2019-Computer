package timer;

import constant.MAZConstant;
import event.ATimer;

public class ZoomPercentPaintTimer extends ATimer{

	// Attributes
	private static int checkInterval = 10;
	
	// Working Variable
	private int leftTime;
	private boolean nowMinus;
	
	// Constructor
	public ZoomPercentPaintTimer() {super(checkInterval, true); this.leftTime = 0; this.nowMinus = true;}
	
	@Override protected void timeOutTask() {if(this.leftTime > 0 && this.nowMinus) {this.leftTime-=checkInterval;}}
	
	// Getter & Setter
	public int getZoomPercentPaintTime() {return leftTime;}
	public void reset() {this.leftTime = MAZConstant.ZoomPercentPaintTime;}
}
