package event;

public abstract class ATimer implements Runnable{

	// Attributes
	private int time;
	private boolean repeat;
	
	// Constructor
	public ATimer (int time, boolean repeat) {
		this.time=time;
		this.repeat=repeat;
	}
	
	@Override
	public void run() {
		do {
			this.sleep(this.time);
			this.timeOutTask();
		}while(this.repeat);
	}
	
	// Abstract Method
	protected abstract void timeOutTask();
	
	// Encapsulation Method
	public void startTimer() {new Thread(this).start();}
	public void sleep(int ms) {try {Thread.sleep(ms);} catch (InterruptedException e) {}}
}
