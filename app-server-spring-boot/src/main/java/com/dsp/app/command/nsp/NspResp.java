package com.dsp.app.command.nsp;

import java.util.List;

import com.dsp.dom.nsp.NSP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NspResp {
	private String id;
	private Integer no;
	private Integer frequency;
	private Integer noiseFrequency;
	private Integer numberOfSamples;
	private Integer samplingRate;
	private List<String> imageList;
	
	public static NspResp fromDomain(NSP nsp) {
		return new NspResp(nsp.getId(), nsp.getNo(), nsp.getFrequency(), nsp.getNoiseFrequency(), nsp.getNumberOfSamples(), nsp.getSamplingRate(), nsp.getImageList());
	}
}
