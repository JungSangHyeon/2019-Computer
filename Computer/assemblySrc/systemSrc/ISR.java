package assemblySrc.systemSrc;

import assemblySrc.aExe.Exe;
import constant.Constant;

public class ISR extends Exe{
	
	// Attributes
	int rountineStartCodeLength = 3; // FinderÀÇ ÁÙ ¼ö.
	int pcbSize = Constant.pcbSize;
	
	public ISR() {
		this.setInstructions(new int[][] {
			// Finder
			{ADDI, rountineStartCodeLength},
			{ADDI, pcbSize},
			{GOTOACA, NULL},
			
			// Interrupt Service Routines
			{ProcessTerminatedProcess, NULL},
			{ProcessTimeOut, NULL},
			{ProcessRemove, NULL},
			{ProcessRestore, NULL},
			{LoadProcess, NULL},
		});
	}
}
