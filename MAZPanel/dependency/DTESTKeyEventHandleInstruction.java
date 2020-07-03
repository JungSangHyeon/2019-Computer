package dependency;

import java.awt.event.KeyEvent;

import constant.MAZConstant;
import interfaces.IKeyEventHandleInstruction;

public class DTESTKeyEventHandleInstruction implements IKeyEventHandleInstruction{

	@Override
	public void pressAction(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {System.exit(0);}
		else if(e.getKeyCode() == KeyEvent.VK_1) {MAZConstant.BlackModeOn();}
		else if(e.getKeyCode() == KeyEvent.VK_2) {MAZConstant.WhiteModeOn();}
	}

	@Override public void releaseAction(KeyEvent e) {}
}
