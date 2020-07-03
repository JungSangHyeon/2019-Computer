package main;

import view.MainFrame;

public class Main { // Use Scroll & F1, F2
	public static void main(String[] args) {
		MainFrame mazFrame = new MainFrame(false); // MainFrame (boolean debug);
		mazFrame.init();
		mazFrame.start();
	}
}
