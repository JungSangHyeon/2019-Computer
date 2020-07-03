package event;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import interfaces.IKeyEventHandleInstruction;

public class KeyDispatcher implements KeyEventDispatcher {
	
	// Association
	private IKeyEventHandleInstruction keyEventHandleInstruction;
	
	// Constructor
	public KeyDispatcher() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}
	
	public void init(IKeyEventHandleInstruction keyEventHandleInstruction) {this.keyEventHandleInstruction=keyEventHandleInstruction;}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {this.keyEventHandleInstruction.pressAction(e);}
		else if (e.getID() == KeyEvent.KEY_RELEASED) {this.keyEventHandleInstruction.releaseAction(e);}
		return false;
	}
}
