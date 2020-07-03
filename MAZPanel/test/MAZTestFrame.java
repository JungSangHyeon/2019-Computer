package test;

import javax.swing.JFrame;

import dependency.DTESTKeyEventHandleInstruction;
import dependency.DTESTMouseEventHandleInstruction;
import dependency.DTESTPaintInstruction;
import view.MAZPanel;

@SuppressWarnings("serial")
public class MAZTestFrame extends JFrame{

	// Component
	private MAZPanel mazPanel1, mazPanel2, mazPanel3;
	
	// Constructor
	public MAZTestFrame(boolean debug) {
		// Set Attributes
		if(debug) {
			this.setSize(800, 600);
			this.setLocationRelativeTo(null);
		}else {
			this.setExtendedState(MAXIMIZED_BOTH);
			this.setUndecorated(true);
		}
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Create Component
		this.mazPanel1 = new MAZPanel();
		this.mazPanel1.setKeyEventHandleInstruction(new DTESTKeyEventHandleInstruction());
		this.mazPanel1.setMouseEventHandleInstruction(new DTESTMouseEventHandleInstruction());
		this.mazPanel1.setPaintInstruction(new DTESTPaintInstruction());
		this.mazPanel1.setBounds(100, 100, 500, 900);
		this.add(this.mazPanel1);
		
		this.mazPanel2 = new MAZPanel();
		this.mazPanel2.setKeyEventHandleInstruction(new DTESTKeyEventHandleInstruction());
		this.mazPanel2.setMouseEventHandleInstruction(new DTESTMouseEventHandleInstruction());
		this.mazPanel2.setPaintInstruction(new DTESTPaintInstruction());
		this.mazPanel2.setBounds(700, 300, 200, 200);
		this.add(this.mazPanel2);
		
		this.mazPanel3 = new MAZPanel();
		this.mazPanel3.setKeyEventHandleInstruction(new DTESTKeyEventHandleInstruction());
		this.mazPanel3.setMouseEventHandleInstruction(new DTESTMouseEventHandleInstruction());
		this.mazPanel3.setPaintInstruction(new DTESTPaintInstruction());
		this.mazPanel3.setBounds(1000, 50, 900, 900);
		this.add(this.mazPanel3);
	}

	public void init() {this.mazPanel1.init(); this.mazPanel2.init(); this.mazPanel3.init();}
	public void start() {this.mazPanel1.start(); this.mazPanel2.start(); this.mazPanel3.start(); this.setVisible(true);}
}
