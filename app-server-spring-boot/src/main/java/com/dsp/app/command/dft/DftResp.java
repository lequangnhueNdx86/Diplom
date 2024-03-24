package com.dsp.app.command.dft;

import java.util.List;

import com.dsp.dom.dft.DFT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DftResp {
	private String id;
	private String func1;
	private String func2;
	private int type;
	private int no;
	private List<String> imageList;
	private String alpha;
	private String beta;
	
	public static DftResp fromDomain(DFT dft) {
		return new DftResp(dft.getId(), dft.getFunc1().toString(), dft.getFunc2().toString(), dft.getType().value, dft.getNo(), dft.getImageList(), dft.getAlpha(), dft.getBeta());
	}
}
