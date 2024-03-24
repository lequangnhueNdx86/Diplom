package com.dsp.dom.dft;

public enum DftType {
	LOW(0), HIGH(1);
	public int value;

	private DftType(int value) {
		this.value = value;
	}
}
