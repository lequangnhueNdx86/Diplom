package com.dsp.app.command.fft;

import java.util.List;

import com.dsp.dom.fft.FFT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FftResp {
	private Integer no;
	private String[][] matrixResult;
	private List<String> listResult;
	public static FftResp fromDomain(FFT timeFFT) {
		return new FftResp(timeFFT.getNo(), timeFFT.getResult().getMatrixValue(), timeFFT.getResult().getResult());
	}

}
