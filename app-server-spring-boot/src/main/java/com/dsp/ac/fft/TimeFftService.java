package com.dsp.ac.fft;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dsp.dom.fft.CalculationResult;
import com.dsp.dom.fft.FFT.Require;

@Service
public class TimeFftService implements Require {
	@Value("${dsp-service.host}")
	private String dspServer;
	@Override
	public CalculationResult calc(List<Double> input) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<FftCommand> request = new HttpEntity<FftCommand>(new FftCommand(input));
		FftResp resp = restTemplate.postForObject(dspServer + "/fft/time", request, FftResp.class);
		
		return new CalculationResult(Arrays.asList(resp.getListResult()), resp.getMatrix());
	}

}
