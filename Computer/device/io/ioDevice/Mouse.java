package device.io.ioDevice;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import constant.Constant;
import device.io.EIDataType;
import device.io.EIOID;
import device.io.aIODevice.IODevice;
import device.io.ioController.IOController;
import interfaces.IMouseEventHandleInstruction;

public class Mouse extends IODevice implements IMouseEventHandleInstruction{

	// Constructor
	public Mouse() {
		super(Constant.NoProcess);
		this.id = EIOID.eMouse;
	}
	
	@Override public void init(IOController ioController) {super.init(ioController);}
	@Override public void cMouseMoved(MouseEvent e) {this.setMouseXY(e);}
	@Override public void cMouseDragged(MouseEvent e) {this.setMouseXY(e);}
	
	@Override
	public void cMousePressed(MouseEvent e) {
		this.ioController.acquire();
		this.ioController.writeToMemory(EIDataType.eMousePress.getAddress(), 1);
		this.ioController.release();
	}
	
	private void setMouseXY(MouseEvent e) {
		if(this.ioController != null) {
			this.ioController.acquire();
			this.ioController.writeToMemory(EIDataType.eMouseX.getAddress(), e.getX());
			this.ioController.writeToMemory(EIDataType.eMouseY.getAddress(), e.getY());
			this.ioController.release();
		}
	}
	
	// No Use
	@Override public void process() {}
	@Override public void addData(int data) {}
	@Override public void cMouseWheelMoved(MouseWheelEvent e) {}
	@Override public void cMouseReleased(MouseEvent e) {}
	@Override public void cMouseClicked(MouseEvent e) {}
	@Override public void cMouseEntered(MouseEvent e) {}
	@Override public void cMouseExited(MouseEvent e) {}
}
