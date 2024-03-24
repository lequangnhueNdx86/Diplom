package com.dsp.app.command.fft;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dsp.ac.fft.TimeFftService;
import com.dsp.dom.fft.FFT;
import com.dsp.dom.fft.FftRepository;
import com.dsp.shared.auth.AppContexts;
import com.dsp.shared.auth.JwtTokenProvider;
import com.dsp.shared.base.CommandHandlerReturn;

@Service
public class CalculationTimeFftCommandHandler extends CommandHandlerReturn<CalculationTimeFftCommand, FftResp> {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private TimeFftService timeFftService;
	@Autowired
	private FftRepository timeFftRepository;

	@Override
	public FftResp handle(CalculationTimeFftCommand command) throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		FFT timeFFT = command.toDomain();
		timeFFT.setUserId(userId);
		timeFFT.calculateTimeFFT(timeFftService);
		Optional<FFT> timeFFTOpt = timeFftRepository.getLatestByUserIdAndType(userId, 0);
		if (timeFFTOpt.isPresent()) {
			timeFFT.setNo(timeFFTOpt.get().getNo() + 1);
		} else {
			timeFFT.setNo(1);
		}
		timeFFT = timeFftRepository.insert(timeFFT);
		return FftResp.fromDomain(timeFFT);
	}

}
