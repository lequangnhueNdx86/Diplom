package com.dsp.app.command.nsp;

import com.dsp.dom.nsp.NSP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculationNspCommand {
	private Integer frequency;
	private Integer noiseFrequency;
	private Integer numberOfSamples;
	private Integer samplingRate;
	
	public NSP toDomain() {
		return new NSP(frequency, noiseFrequency, numberOfSamples, samplingRate);
	}
}
