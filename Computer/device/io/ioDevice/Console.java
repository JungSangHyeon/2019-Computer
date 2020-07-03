package device.io.ioDevice;

import java.util.Scanner;
import java.util.Vector;

import constant.Constant;
import device.io.EIDataType;
import device.io.EIOID;
import device.io.aIODevice.IODevice;
import device.io.ioController.IOController;
import visualizer.ConsoleVisualizer;

public class Console extends IODevice{

	// Component
	private ConsoleVisualizer consoleVisualizer;
	private Vector<String> strings;
	
	// Constructor
	public Console() {
		super(Constant.ProcessTime);
		this.id = EIOID.eConsole;
		this.strings = new Vector<String>();
		this.consoleVisualizer = new ConsoleVisualizer();
		this.setVisualizer(this.consoleVisualizer);
	}

	@Override
	public void init(IOController ioController) {
		super.init(ioController);
		this.consoleVisualizer.init(this.strings);
		this.strings.add(Constant.ConsoleDefaultMessage);
	}
	
	@Override
	public void process() {// keyboard 값으로 작동
		this.ioController.acquire();
		this.addToStrings(this.ioController.readFromMemory(EIDataType.eKeyCode.getAddress()));
		this.ioController.writeToMemory(EIDataType.eKeyCode.getAddress(), 0);
		this.ioController.release();
	}

	@Override
	public void addData(int data) {this.strings.add(Integer.toString(data));}// CU code로 작동
	
	private void addToStrings(int now) {
		if(31 < now && now < 127) { // 문자만 필터링.
			String newString = this.strings.lastElement() + (char) now;
			this.strings.set(this.strings.size()-1, newString);
		} else if(now == 10) {//enter
			this.processInstruction(now);
		} else if(now == 8) {//back space
			String result = this.strings.lastElement();
			if(result.length()!=0) {
				result = result.substring(0, result.length()-1);
				this.strings.remove(this.strings.size()-1);
				this.strings.add(result);
			}
		} 
	}

	private void processInstruction(int now) {
		try {
			String instruction = this.strings.lastElement();
			Scanner sc = new Scanner(instruction);
			if(sc.next().equals(Constant.ExecuteSymbol)) {
				int exeID = this.ioController.getExeID(sc.next());
				if(exeID != -1) {this.ioController.writeToMemory(EIDataType.eConsoleLoad.getAddress(), exeID);}
				else {this.strings.add("System ) \""+this.strings.lastElement().split(" ")[1] +"\""+ Constant.ExecuteErrMsg);}
			}
			this.strings.add("");
			sc.close();
		}catch(Exception e) {/*명령어 오타 처리 -> 오타면 처리 안함*/}
	}
}
