package soundPlayer;

import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundPlayer {

	// User Value
	private int samplingRate = SoundPlayerConstant.SAMPLING_RATE;
	private int sampleSize = SoundPlayerConstant.SAMPLE_SIZE_IN_BYTES;
	private double bufferDuration = SoundPlayerConstant.BUFFER_DURATION;
	private double freqCyclePosition = SoundPlayerConstant.FREQ_CYCLE_POSITION;
	private AudioFormat audioFormat = SoundPlayerConstant.AUDIO_FORMAT;
	private String lineUnavailableExceptionErrMsg = SoundPlayerConstant.LINE_UNAVAILABLE_EXCEPTION_ERR_MSG;
	
	// System Value
	private int sinePacketSize = (int) (this.bufferDuration * this.samplingRate * this.sampleSize);
	private SourceDataLine line;
	private ByteBuffer buff;
	
	public void init() {
		try {
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, this.audioFormat, this.sinePacketSize * 2);
			if (!AudioSystem.isLineSupported(info)) {throw new LineUnavailableException();}
			this.line = (SourceDataLine) AudioSystem.getLine(info);
			this.line.open(this.audioFormat);
			this.line.start();
		} catch (LineUnavailableException e) {System.out.println(this.lineUnavailableExceptionErrMsg); System.exit(-1);}
		this.buff = ByteBuffer.allocate(this.sinePacketSize);
	}
	
	public void playSound(double freq) {
	    double freqCycleInc = freq / this.samplingRate;
		this.buff.clear();
		for (int i = 0; i < this.sinePacketSize / this.sampleSize; i++) {
			this.buff.putShort((short) (Short.MAX_VALUE * Math.sin(2 * Math.PI * this.freqCyclePosition)));
			this.freqCyclePosition += freqCycleInc;
			if (this.freqCyclePosition > 1) {this.freqCyclePosition -= 1;}
		}
		this.line.write(this.buff.array(), 0, this.buff.position());
		try {while (this.line.getBufferSize() - this.line.available() > this.sinePacketSize) {Thread.sleep(1);}} // Using Buffer Size > Sine Packet Size
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
