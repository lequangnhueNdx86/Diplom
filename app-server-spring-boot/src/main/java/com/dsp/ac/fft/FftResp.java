package com.dsp.ac.fft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FftResp {
	private String[] listResult;
	private String[][] matrix;
}
