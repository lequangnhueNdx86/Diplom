package com.dsp.app.command.fft;

import java.util.List;
import java.util.stream.Collectors;

import com.dsp.dom.fft.FFT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CalculationFreqFftCommand {
	private List<String> inputList; 
	public FFT toDomain() {
		return new FFT(inputList.stream().map(Double::parseDouble).collect(Collectors.toList()), 1);
	}

}
