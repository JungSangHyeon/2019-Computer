package assemblySrc.applicationSrc;

import assemblySrc.aExe.Exe;
import device.hdd.HDD.EExe;

public class ThreadTest extends Exe{
	
	public ThreadTest() {
		this.setInstructions(new int[][] {
			{MakeThread, EExe.ePrintTest.ordinal()}, 
			{MakeThread, EExe.ePrintTest.ordinal()}, 
			{MakeThread, EExe.ePrintTest.ordinal()}, 
			{HALT, NULL}
		});
	}
}
