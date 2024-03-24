package com.dsp.ac.nsp;

import java.io.Serializable;

import com.dsp.ac.dft.DftCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NspCommand implements Serializable {
	private Integer frequency;
	private Integer noiseFrequency;
	private Integer numberOfSamples;
	private Integer samplingRate;

}
