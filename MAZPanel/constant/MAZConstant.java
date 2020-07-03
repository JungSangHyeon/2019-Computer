package constant;

import java.awt.Color;

public class MAZConstant { // 시간 많을 때, 스태틱 없애고 얘를 다 붙여주자.

	// MAZ Panel
	public static Color PanelColor = Color.WHITE;
	public static int PanelRepaintInterval = 40;
	
	// MAZ Handler
	public static int CoordinatePaintTime = 1000;
	public static int ZoomPercentPaintTime = 1000;
	
	// MAZ Core
	public static int MaxZoomLevel = 9;
	public static int MinZoomLevel = -20;
	public static float ZoomFactor = 1.2f;
	
	// MAZ Paint
	public static int LineWidth = 1;
	public static int LineInterval = 50;
	public static int LineAlpaLimit = 110;
	public static int ZoomPercentGranulity = 5;
	public static int ZoomPercentTextSize = 20;
	public static int ZoomBlockFixedAlpaAreaValue = 100;
	public static int ZoomBlockFixedAlpaValue = 220;
	public static int BlockWidth = 94, Blockheight = 46;
	public static Color CoordColor = new Color(0, 0, 0);
	public static Color BackgroundColor = new Color(255, 255, 255);
	public static Color ZoomBlockColor = new Color(100, 100, 100);
	public static Color ZoomBlockTextColor = new Color(255, 255, 255);
	
	// Mode - TRASH @수정요@
	public enum eMode {WhiteMode, BlackMode}
	private static eMode nowMode = eMode.WhiteMode;
	public static eMode getNowMode() {return MAZConstant.nowMode;}
	
	public static void BlackModeOn() {
		MAZConstant.nowMode = eMode.BlackMode;
		MAZConstant.LineWidth = 3;
		MAZConstant.CoordColor = new Color(0, 220, 0);
		MAZConstant.BackgroundColor = new Color(0, 0, 0);
		MAZConstant.ZoomBlockColor = new Color(0, 120, 0);
		MAZConstant.ZoomBlockTextColor = new Color(0, 0, 0);
	}

	public static void WhiteModeOn() {
		MAZConstant.nowMode = eMode.WhiteMode;
		MAZConstant.LineWidth = 1;
		MAZConstant.CoordColor = new Color(0, 0, 0);
		MAZConstant.BackgroundColor = new Color(255, 255, 255);
		MAZConstant.ZoomBlockColor = new Color(100, 100, 100);
		MAZConstant.ZoomBlockTextColor = new Color(255, 255, 255);
	}
}
