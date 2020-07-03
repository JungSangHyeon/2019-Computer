package assemblySrc.systemSrc;

import assemblySrc.aExe.Exe;

public class ProcessManager extends Exe{
	
	// Attributes
	private int begin = 0 + this.pcbSize;
	private int jmp1 = 4 + this.pcbSize;
	private int readyQueueStart = 2;
	
	// Attributes - Address
	private int aIndex = 0;
	private int aSize = 1;
	
	public ProcessManager() {
		this.setInstructions(new int[][] {
			// Size = 0 이면 처음으로. 
			// if(aSize.getData() == 0){goTo(begin);}
			{LDAI, aSize}, // @begin@
			{CMPI, 0},
			{JNEQ, jmp1},
			{GOTO, begin},
						
			// 현재 Index의 Process 실행.
			// int queueAddress =  aIndex.getValue + readyQueueStart;
			// int processAddress = queueAddress.getData();
			// goToABSA(processAddress); 
			{LDAI, aIndex}, // @jmp1@
			{ADDI, readyQueueStart},
			{LDACAI, NULL},
			{GOTOABSA, NULL},
			
			// Index++
			// aIndex.setData(aIndex.getData() + 1);
			{LDAI, aIndex},
			{ADDI, 1},
			{STA, aIndex},
			
			// index = size면 index 0; goTo(jmp1);
			// if(aIndex.getData() < aSize.getData()){goTo(jmp1);}
			// else{aIndex.setData(0); goTo(jmp1);}
			{CMPAI, aSize},
			{JLE, begin},
			{LDI, 0},
			{STA, aIndex},
			{GOTO, begin},
		});
	}
}
