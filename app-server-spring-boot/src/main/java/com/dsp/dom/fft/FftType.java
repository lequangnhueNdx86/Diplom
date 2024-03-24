package com.dsp.dom.fft;

public enum FftType {
	TIME(0), FREQ(1);
	public int value;

	private FftType(int value) {
		this.value = value;
	}
	
	
}
