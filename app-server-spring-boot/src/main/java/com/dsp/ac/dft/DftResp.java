package com.dsp.ac.dft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DftResp {
	private String[] listResult;
	private String alpha;
	private String beta;
}
