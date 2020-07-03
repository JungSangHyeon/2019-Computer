package assemblySrc.applicationSrc;

import assemblySrc.aExe.Exe;

public class PrintTest extends Exe{
	
	public PrintTest() {
		this.setInstructions(new int[][] {
			{LDI, 1}, {PRTAC, NULL},
			{LDI, 2}, {PRTAC, NULL},
			{LDI, 3}, {PRTAC, NULL},
			{LDI, 4}, {PRTAC, NULL},
			{LDI, 5}, {PRTAC, NULL},
			{HALT, NULL}
		});
	}
}
