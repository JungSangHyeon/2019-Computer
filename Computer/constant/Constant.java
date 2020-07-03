package constant;

public class Constant {
	
	// Console
	public static String ConsoleDefaultMessage = "2019 JSH Final Computer";
	public static String ExecuteSymbol = "run";
	public static String ExecuteErrMsg = " is not exe file.";
	
	// Memory
	public static int MemorySize = 280;
	
	// Screen
	public static int ScreenWidth = 1920;
	public static int ScreenHeight = 1080;
	public static int DebugScreenWidth = 1200;
	public static int DebugScreenHeight = 600;
	
	// Clock
	public static int ClockSpeed = 10;
	
	// Page Size
	public static int processSize = 40;
	
	// OS Address
	public static int ProcessManagerStartAddress = Constant.processSize*0;  
	public static int ISRStartAddress = Constant.processSize*1;
	public static int DMAStartAddress = Constant.processSize*2;
	public static int APP_START_ADDRESS = Constant.processSize*3;
	
	// PCB
	public enum PCBIndex{AC, STATUS, CP, SP, PC}
	public static final int pcbSize = PCBIndex.values().length;
		
	// Process Manager
	public static int PM_SIZE = 16;
	public static int PM_QUEUE_INDEX_ADDRESS = Constant.ProcessManagerStartAddress + Constant.PM_SIZE + Constant.pcbSize;
	public static int PM_QUEUE_SIZE_ADDRESS = Constant.ProcessManagerStartAddress + Constant.PM_SIZE + Constant.pcbSize + 1;
	public static int PM_QUEUE_START_ADDRESS = 2; // after index & size
	
	// IO
	public static int ProcessTime = 10;
	public static int NoProcess = 1000;
	
	// Interrupt
	public static int TIME_OUT_LINE_NUM = 20;
	public enum EInterruptID{ // 순서 바꾸지 마십셔. ISR과 세트입니다.
		processTerminated, timeOut, remove, restore, loadProcess;
		public int getID() {return this.ordinal();}
	}
}
