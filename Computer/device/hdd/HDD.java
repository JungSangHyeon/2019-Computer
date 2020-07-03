package device.hdd;

import assemblySrc.aExe.Exe;
import assemblySrc.applicationSrc.PrintTest;
import assemblySrc.applicationSrc.Airplane;
import assemblySrc.applicationSrc.SpeakerTest;
import assemblySrc.applicationSrc.Sum1To10Exe;
import assemblySrc.applicationSrc.ThreadTest;
import assemblySrc.systemSrc.DMA;
import assemblySrc.systemSrc.ISR;
import assemblySrc.systemSrc.ProcessManager;
import device.aDevice.Device;
import device.motherBoard.interfaces.HDDInterface;
import visualizer.HDDVisualizer;

public class HDD extends Device implements HDDInterface {
	
	public enum EExe {
		// System
		eNULL(null, ""), // must 0 index
		
		// OS
		eProcessManager(new ProcessManager(), ""),
		eISR(new ISR(), ""),
		eIODataMemory(new DMA(), ""),
		
		// Application
		ePrintTest(new PrintTest(), "PrintTest"),
		eSum1To10Exe(new Sum1To10Exe(), "1to10Sum"),
		eThreadTest(new ThreadTest(), "ThreadTest"),
		eSpeakerTest(new SpeakerTest(), "SpeakerTest"),
		eAirplane(new Airplane(), "Airplane"),
		;
		
		private Exe exe; private String name;
		private EExe(Exe exe, String name) {this.exe=exe; this.name=name;}
		public Exe getExe() {return this.exe;}
		public String getName() {return this.name;}
	}
	
	// Constructor
	public HDD() {this.setVisualizer(new HDDVisualizer(EExe.values()));}
	
	// Getter & Setter
	@Override public Exe getExe(int id) {return EExe.values()[id].getExe();}
	@Override public String getName(int id) {return EExe.values()[id].getName();}
	@Override public int getExeID(String name) {for (EExe eexe : EExe.values()) {if(eexe.getName().equals(name)) {return eexe.ordinal();}}return -1;}
}