package view;

import javax.swing.JFrame;

import computer.Computer;
import constant.Constant;
import mazDependency.DPaintInstruction;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	// Component
	private MAZPanel mazPanel;
	private Computer computer;
	
	// Constructor
	public MainFrame(boolean debug) {
		// Set Attributes
		if(debug) {
			this.setSize(Constant.DebugScreenWidth, Constant.DebugScreenHeight);
			this.setLocationRelativeTo(null);
		}else {
			this.setExtendedState(MAXIMIZED_BOTH);
			this.setUndecorated(true);
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Create Component
		this.computer = new Computer();
		this.mazPanel = new MAZPanel();
		this.mazPanel.setKeyEventHandleInstruction(this.computer.getKeyboard());
		this.mazPanel.setMouseEventHandleInstruction(this.computer.getMouse());
		this.mazPanel.setPaintInstruction(new DPaintInstruction(this.computer.getDevices()));
		this.mazPanel.setSize(Constant.ScreenWidth, Constant.ScreenHeight);
		this.add(this.mazPanel);
	}

	public void init() {this.computer.init(); this.mazPanel.init();}

	public void start() {
		this.mazPanel.start();
		this.setVisible(true);
		this.computer.powerOn();
	}
}
