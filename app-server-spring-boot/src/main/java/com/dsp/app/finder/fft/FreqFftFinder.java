package com.dsp.app.finder.fft;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.app.command.fft.FftResp;
import com.dsp.dom.fft.FFT;
import com.dsp.dom.fft.FftRepository;
import com.dsp.shared.auth.AppContexts;
import com.dsp.shared.auth.JwtTokenProvider;

@Service
public class FreqFftFinder {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private FftRepository freqFftRepository;
	
	public FftResp getFreqFftByUserId() throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		Optional<FFT> fft = freqFftRepository.getLatestByUserIdAndType(userId, 1);
		return fft.isPresent() ? FftResp.fromDomain(fft.get()) : null;
	}
	
	public long count() throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		return freqFftRepository.count(userId, 1);
	}
}
