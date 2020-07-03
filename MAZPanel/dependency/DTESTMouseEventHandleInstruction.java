package dependency;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import interfaces.IMouseEventHandleInstruction;

public class DTESTMouseEventHandleInstruction implements IMouseEventHandleInstruction{
	@Override public void cMouseWheelMoved(MouseWheelEvent e) {}
	@Override public void cMouseDragged(MouseEvent e) {}
	@Override public void cMousePressed(MouseEvent e) {}
	@Override public void cMouseReleased(MouseEvent e) {}
	@Override public void cMouseClicked(MouseEvent e) {}
	@Override public void cMouseEntered(MouseEvent e) {}
	@Override public void cMouseExited(MouseEvent e) {}
	@Override	public void cMouseMoved(MouseEvent e) {}
}
