package com.dsp.dom.dft;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalcResp {
	private List<String> listResult;
	private String alpha;
	private String beta;
}
