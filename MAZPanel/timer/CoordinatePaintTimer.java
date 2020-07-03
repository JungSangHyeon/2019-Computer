package timer;

import constant.MAZConstant;
import event.ATimer;

public class CoordinatePaintTimer extends ATimer{

	// Attributes
	private static int checkInterval = 10;
	
	// Working Variable
	private int leftTime;
	private boolean nowMinus;
	
	// Constructor
	public CoordinatePaintTimer() {super(checkInterval, true); this.leftTime = 0; this.nowMinus = true;}
	
	@Override protected void timeOutTask() {if(this.leftTime > 0 && this.nowMinus) {this.leftTime-=checkInterval;}}
	
	// Getter & Setter
	public int getCoordinatePaintTime() {return leftTime;}
	public void setNowMinus(boolean boo) {this.nowMinus = boo;}
	public void reset() {this.leftTime = MAZConstant.CoordinatePaintTime;}
}
