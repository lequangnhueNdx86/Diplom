package com.dsp.dom.fft;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class FFT {
	@Setter
	private String id;
	@Setter
	private String userId;
	private List<Double> inputValue;
	private CalculationResult result;
	@Setter
	private Integer no;
	private String createdAt;
	private FftType type;
	
	public FFT(List<Double> inputValue, int type) {
		this.inputValue = inputValue;
		this.type = type == 0 ? FftType.TIME : FftType.FREQ;
	}
	
	public void calculateTimeFFT(Require require) {
		this.result = require.calc(inputValue); 
	}

	public static interface Require {
		CalculationResult calc(List<Double> input);
	}
}
