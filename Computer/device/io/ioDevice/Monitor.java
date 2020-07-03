package device.io.ioDevice;

import constant.Constant;
import device.io.EIDataType;
import device.io.EIOID;
import device.io.aIODevice.IODevice;
import device.io.ioController.IOController;
import visualizer.monitorVisualizer.MonitorVisualizer;

public class Monitor extends IODevice{

	// Component
	private MonitorVisualizer monitorVisualizer;
	
	// Constructor
	public Monitor() {
		super(Constant.ProcessTime);
		this.id = EIOID.eMonitor;
		this.monitorVisualizer = new MonitorVisualizer();
		this.setVisualizer(this.monitorVisualizer);
	}

	@Override public void init(IOController ioController) {super.init(ioController);}
	
	@Override
	public void process() {
		this.ioController.acquire();
		int x = this.ioController.readFromMemory(EIDataType.eMouseX.getAddress());
		int y = this.ioController.readFromMemory(EIDataType.eMouseY.getAddress());
		this.monitorVisualizer.setMouseXY(x, y);
		if(this.ioController.readFromMemory(EIDataType.eMousePress.getAddress()) == 1) {// is mouse pressed
			int nowExecutedExeId = this.monitorVisualizer.getExecutedExeID();
			if(nowExecutedExeId != -1) {this.ioController.writeToMemory(EIDataType.eMonitorLoad.getAddress(), nowExecutedExeId);}
			this.ioController.writeToMemory(EIDataType.eMousePress.getAddress(), 0); // reset mouse pressed
		}
		this.ioController.release();
	}

	// No Use
	@Override public void addData(int data) {}
}
