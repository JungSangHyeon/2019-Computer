package assemblySrc.applicationSrc;

import assemblySrc.aExe.Exe;

public class Airplane extends Exe{
	
	private enum Note {
		Do(1047), Re(1175), Mi(1319), Fa(1397), Sol(1568),La(1760), Si(1976), DDO(2093);
		
		private int freq;
		private Note(int freq) {this.freq=freq;}
		public int getFreq() {return this.freq;}
	}
	
	public Airplane() {
		this.setInstructions(new int[][] {
			{SetFreq, Note.Mi.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Re.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Do.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Re.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Mi.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Mi.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Mi.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Re.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Re.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Mi.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Re.getFreq()}, {PlaySpk, 1000},
			{SetFreq, Note.Do.getFreq()}, {PlaySpk, 1000},
			{HALT, NULL}
		});
	}
}
