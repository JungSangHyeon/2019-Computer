package device.io.ioDevice;

import java.awt.event.KeyEvent;

import constant.Constant;
import constant.MAZConstant;
import device.io.EIDataType;
import device.io.EIOID;
import device.io.aIODevice.IODevice;
import device.io.ioController.IOController;
import interfaces.IKeyEventHandleInstruction;

public class Keyboard extends IODevice implements IKeyEventHandleInstruction{

	// Constructor
	public Keyboard() {
		super(Constant.NoProcess);
		this.id = EIOID.eKeyboard;
	}
	
	@Override public void init(IOController ioController) {super.init(ioController);}

	@Override
	public void pressAction(KeyEvent e) {
		int nowKey = e.getKeyCode();
		if(nowKey == KeyEvent.VK_ESCAPE) {System.exit(0);}
		else if(nowKey == KeyEvent.VK_F1) {MAZConstant.BlackModeOn();}
		else if(nowKey == KeyEvent.VK_F2) {MAZConstant.WhiteModeOn();}
		else {
			this.ioController.acquire();
			if(!e.isShiftDown() && (64 < nowKey && nowKey < 91)) {this.ioController.writeToMemory(EIDataType.eKeyCode.getAddress(), e.getKeyCode()+32);}
			else {this.ioController.writeToMemory(EIDataType.eKeyCode.getAddress(), e.getKeyCode());}
			this.ioController.release();
		}
	}

	// No Use
	@Override public void process() {}
	@Override public void addData(int data) {}
	@Override public void releaseAction(KeyEvent e) {}
}
