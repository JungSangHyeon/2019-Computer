package assemblySrc.applicationSrc;

import assemblySrc.aExe.Exe;

public class Sum1To10Exe extends Exe{
	
	// Attributes
	private int end = 10;
	private int loop1Start = 2 + this.pcbSize;
	
	// Attributes - Address
	private int aSum = 0; 
	private int aNowAdd = 1;
	private int aEndNowAdd = 2;
	
	public Sum1To10Exe() {
		this.setInstructions(new int[][] {
			// EndNowAdd = end;
			{LDI, end},
			{STA, aEndNowAdd},
			
			// Sum += NowAdd;
			{LDAI, aSum}, // @loop1Start@
			{ADDAI, aNowAdd},
			{STA, aSum},
			
			// Print AC
			{PRTAC, NULL},
			
			// aNowAdd++;
			{LDAI, aNowAdd},
			{ADDI, 1},
			{STA, aNowAdd},
			
			// if(aNowAdd > aEndNowAdd){halt();}
			// else{goto(loop1Start);}
			{CMPAI, aEndNowAdd},
			{JLEE, loop1Start},
			
			// End Process
			{HALT, NULL},
		});
	}
}
