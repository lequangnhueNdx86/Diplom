package com.dsp.app.command.dft;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.ac.dft.DftService;
import com.dsp.dom.dft.DFT;
import com.dsp.dom.dft.DftRepository;
import com.dsp.shared.auth.AppContexts;
import com.dsp.shared.auth.JwtTokenProvider;
import com.dsp.shared.base.CommandHandlerReturn;

@Service
public class CalculationDftCommandHandler extends CommandHandlerReturn<CalculationDftCommand, DftResp>{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private DftRepository dftRepository;
	@Autowired
	private DftService dftService;
	
	@Override
	public DftResp handle(CalculationDftCommand command) throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		DFT dft = command.toDomain();
		dft.setUserId(userId);
		dft.calc(dftService);
		Optional<DFT> dftOpt = dftRepository.getLatestByUserId(userId);
		if (dftOpt.isPresent()) {
			dft.setNo(dftOpt.get().getNo() + 1);
		} else {
			dft.setNo(1);
		}
		dft = dftRepository.insert(dft);
		return DftResp.fromDomain(dft);
	}

}
