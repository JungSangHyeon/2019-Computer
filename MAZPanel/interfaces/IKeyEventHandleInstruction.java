package interfaces;

import java.awt.event.KeyEvent;

public interface IKeyEventHandleInstruction {
	void pressAction(KeyEvent e);
	void releaseAction(KeyEvent e);
}
