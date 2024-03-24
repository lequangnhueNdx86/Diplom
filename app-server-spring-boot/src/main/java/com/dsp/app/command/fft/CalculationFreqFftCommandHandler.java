package com.dsp.app.command.fft;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.ac.fft.FreqFftService;
import com.dsp.dom.fft.FFT;
import com.dsp.dom.fft.FftRepository;
import com.dsp.shared.auth.AppContexts;
import com.dsp.shared.auth.JwtTokenProvider;
import com.dsp.shared.base.CommandHandlerReturn;

@Service
public class CalculationFreqFftCommandHandler extends CommandHandlerReturn<CalculationFreqFftCommand, FftResp> {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private FreqFftService freqFftService;
	@Autowired
	private FftRepository fftRepository;

	@Override
	public FftResp handle(CalculationFreqFftCommand command) throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		FFT freqFFT = command.toDomain();
		freqFFT.setUserId(userId);
		freqFFT.calculateTimeFFT(freqFftService);
		Optional<FFT> freqFFTOpt = fftRepository.getLatestByUserIdAndType(userId, 1);
		if (freqFFTOpt.isPresent()) {
			freqFFT.setNo(freqFFTOpt.get().getNo() + 1);
		} else {
			freqFFT.setNo(1);
		}
		freqFFT = fftRepository.insert(freqFFT);
		return FftResp.fromDomain(freqFFT);
	}

}
