package com.dsp.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.app.command.fft.CalculationFreqFftCommand;
import com.dsp.app.command.fft.CalculationFreqFftCommandHandler;
import com.dsp.app.command.fft.CalculationTimeFftCommand;
import com.dsp.app.command.fft.CalculationTimeFftCommandHandler;
import com.dsp.app.finder.fft.FreqFftFinder;
import com.dsp.app.finder.fft.TimeFftFinder;
import com.dsp.shared.base.BaseResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/fft")
public class FftWebService {
	
	@Autowired 
	private CalculationTimeFftCommandHandler calculationTimeFftCommandHandler;
	
	@Autowired
	private CalculationFreqFftCommandHandler calculationFreqFftCommandHandler;
	
	@Autowired
	private TimeFftFinder timeFftFinder;
	
	@Autowired
	private FreqFftFinder freqFftFinder;

	@PostMapping("/time/calc")
	public BaseResponse<?> calcTimeFft(@RequestBody CalculationTimeFftCommand command) throws Exception {
		return BaseResponse.ofSucceeded(calculationTimeFftCommandHandler.handle(command));
	}
	
	@PostMapping("/freq/calc")
	public BaseResponse<?> calcFreqTimeFft(@RequestBody CalculationFreqFftCommand command) throws Exception {
		return BaseResponse.ofSucceeded(calculationFreqFftCommandHandler.handle(command));
	}
	
	@GetMapping("/time")
	public BaseResponse<?> getLatestTimeFft() throws Exception {
		return BaseResponse.ofSucceeded(timeFftFinder.getTimeFftByUserId());
	}
	
	@GetMapping("/freq")
	public BaseResponse<?> getLatestFreqFft() throws Exception {
		return BaseResponse.ofSucceeded(freqFftFinder.getFreqFftByUserId());
	}
	
}
