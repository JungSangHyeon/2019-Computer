package device.cpu.status;

public class StatusControl {
	
	// Global
	public enum EStatusFlag {// 최대 16가지. (SETSTA 때문)
		eSignBit, eZeroBit, eInterruptBit;
		public int getFlagSit() {return this.ordinal();}
	}
	
	public enum EStatus {
		/*Sign*/ SignMinus(0), SignPlus(1),
		/*Zero*/ ZeroFalse(0), ZeroTrue(1),
		/*Interrupt*/ NotInterrupted(0), Interrupted(1),
		;
		private int value;
		private EStatus(int value) {this.value=value;}
		public int getValue() {return this.value;}
	}
	
	public static int getNewStatusValue(EStatusFlag changeTarget, int changeToThis, int nowStatusValue) {
		int sitNum = changeTarget.getFlagSit();
		int sitValue = (int) Math.pow(2, sitNum);
		if(changeToThis == 0) {
			int full1 = 0xffffffff;
			int mask = full1 - sitValue;
			return nowStatusValue & mask;
		}else if(changeToThis == 1) {
			int full0 = 0x00000000;
			int mask = full0 + sitValue;
			return nowStatusValue | mask;
		}else {return 0;}// changeToThis - Not Correct Value Error 
	}
	
	public static int getStatusValue(EStatusFlag target, int nowStatusValue) {
		int sitNum = target.getFlagSit();
		int sitValue = (int) Math.pow(2, sitNum);
		int full0 = 0x00000000;
		int mask = full0 + sitValue;
		nowStatusValue = nowStatusValue & mask;
		if(nowStatusValue > 0) {return 1;}
		else {return 0;}
	}
}
