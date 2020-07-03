package device.cpu.cu;

import device.cpu.cu.cuInstruction.ECUInstruction;
import device.motherBoard.adapter.ROMAdapter;

public class CU {
		
	public void executeBIOS(ROMAdapter romAdapter) {romAdapter.getBIOS().execute();}
	
	public void run() {
		ECUInstruction.eTIMER.execute();
		ECUInstruction.eProcessTimeOutInterrupt.execute();
		
		ECUInstruction.eFetch.execute(); 
		ECUInstruction.eDecode.execute();
		ECUInstruction.eExecute.execute();
		ECUInstruction.eProcessInterrupt.execute();
		
		ECUInstruction.eProcessDMAInterrupt.execute(); // 만약 Interrupt 처리중이라면 대기 함.
	}
}
