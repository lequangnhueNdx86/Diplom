package com.dsp.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.app.finder.dft.DftFinder;
import com.dsp.app.finder.fft.FreqFftFinder;
import com.dsp.app.finder.fft.TimeFftFinder;
import com.dsp.app.finder.nsp.NspFinder;
import com.dsp.query.HomeQueryDto;
import com.dsp.shared.base.BaseResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/home")
public class HomeQueryWebService {
	@Autowired
	private TimeFftFinder timeFftFinder;
	@Autowired
	private FreqFftFinder freqFftFinder;
	@Autowired
	private DftFinder dftFinder;
	@Autowired
	private NspFinder nspFinder;
	
	@GetMapping
	public BaseResponse<?> startHome() throws Exception {
		return BaseResponse.ofSucceeded(new HomeQueryDto(timeFftFinder.count(), freqFftFinder.count(), dftFinder.count(), nspFinder.count()));
	}

}
