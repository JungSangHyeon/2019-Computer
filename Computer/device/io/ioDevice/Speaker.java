package device.io.ioDevice;

import constant.Constant;
import device.io.EIDataType;
import device.io.EIOID;
import device.io.aIODevice.IODevice;
import device.io.ioController.IOController;
import soundPlayer.SoundPlayer;

public class Speaker extends IODevice{

	// Working Variable
	private int nowFreq = 0;
	private long playTime, playStartTime;
	private boolean running = false;
	
	// Component
	private SoundPlayer soundPlayer;
	
	// Constructor
	public Speaker() {
		super(Constant.ProcessTime);
		this.id = EIOID.eSpeaker;
		this.soundPlayer = new SoundPlayer();
	}

	@Override public void init(IOController ioController) {super.init(ioController); this.soundPlayer.init();}
	
	@Override
	public void process() {
		this.ioController.acquire();
		this.nowFreq = this.ioController.readFromMemory(EIDataType.eSpeakerFreq.getAddress());
		this.ioController.release();
		if(this.playTime > System.currentTimeMillis() - this.playStartTime) {this.soundPlayer.playSound(this.nowFreq);}
		else if(this.running) {this.running = false; this.ioController.writeToMemory(EIDataType.eSPKFinish.getAddress(), 1);}
	}

	@Override 
	public void addData(int data) {
		this.playTime = data;
		this.playStartTime = System.currentTimeMillis();
		this.running = true;
	}
}
