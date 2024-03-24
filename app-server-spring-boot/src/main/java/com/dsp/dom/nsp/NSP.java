package com.dsp.dom.nsp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class NSP {
	@Setter
	private String id;
	@Setter
	private Integer no;
	@Setter
	private String userId;
	private int frequency;
	private int noiseFrequency;
	private int numberOfSamples;
	private int samplingRate;
	private String createdAt;
	private List<String> imageList;
	public NSP(int frequency, int noiseFrequency, int numberOfSamples, int samplingRate) {
		super();
		this.frequency = frequency;
		this.noiseFrequency = noiseFrequency;
		this.numberOfSamples = numberOfSamples;
		this.samplingRate = samplingRate;
	}
	
	public void calc(Require require) {
		this.imageList = require.calc(frequency, noiseFrequency, numberOfSamples, samplingRate);
	}
	
	public static interface Require {
		List<String> calc(Integer frequency, Integer noisyFrequency, Integer numberOfSamples, Integer samplingRate);
	}
}
