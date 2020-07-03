package interfaces;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface IMouseEventHandleInstruction {
	void cMouseWheelMoved(MouseWheelEvent e);
	void cMouseDragged(MouseEvent e);
	void cMousePressed(MouseEvent e);
	void cMouseReleased(MouseEvent e);
	void cMouseClicked(MouseEvent e);
	void cMouseEntered(MouseEvent e);
	void cMouseExited(MouseEvent e);
	void cMouseMoved(MouseEvent e);
}
