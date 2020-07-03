package soundPlayer;

import javax.sound.sampled.AudioFormat;

public class SoundPlayerConstant {

	// Frequency
	public static final double FREQ_CYCLE_POSITION = 0;// Position through the sine wave as a percentage (0-1 <=> 0-2*PI)
	public static final double BUFFER_DURATION = 0.0400;
	
	// Player
	public static final String LINE_UNAVAILABLE_EXCEPTION_ERR_MSG = "Line not available";
	
	// Audio Format
	public static final int SAMPLING_RATE = 96000; // 44100, 96000, 176400, 352800
	public static final int SAMPLE_SIZE_IN_BYTES = 2;
	public static final int CHANEL_NUM = 1; // 1 for mono, 2 for stereo
	public static final boolean SIGNED = true;
	public static final boolean BIG_ENDIAN = true;
	
	public static final AudioFormat AUDIO_FORMAT = new AudioFormat(
			SoundPlayerConstant.SAMPLING_RATE,
			SoundPlayerConstant.SAMPLE_SIZE_IN_BYTES * 8, // Bits
			SoundPlayerConstant.CHANEL_NUM,
			SoundPlayerConstant.SIGNED,
			SoundPlayerConstant.BIG_ENDIAN
	);
}
