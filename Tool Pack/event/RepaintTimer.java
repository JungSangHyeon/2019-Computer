package event;

import javax.swing.JPanel;

public class RepaintTimer extends ATimer{

	// Association
	private JPanel panel;
	
	// Constructor
	public RepaintTimer(int time, JPanel panel) {super(time, true); this.panel=panel;}

	@Override protected void timeOutTask() {this.panel.repaint();}
}
