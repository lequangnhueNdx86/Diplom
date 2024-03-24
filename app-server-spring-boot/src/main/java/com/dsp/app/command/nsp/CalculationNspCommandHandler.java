package com.dsp.app.command.nsp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.ac.nsp.NspService;
import com.dsp.dom.nsp.NSP;
import com.dsp.dom.nsp.NspRepository;
import com.dsp.shared.auth.AppContexts;
import com.dsp.shared.auth.JwtTokenProvider;
import com.dsp.shared.base.CommandHandlerReturn;

@Service
public class CalculationNspCommandHandler extends CommandHandlerReturn<CalculationNspCommand, NspResp>{
	@Autowired
	private NspRepository nspRepository;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private NspService nspService;

	@Override
	public NspResp handle(CalculationNspCommand command) throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		NSP nsp = command.toDomain();
		nsp.setUserId(userId);
		nsp.calc(nspService);
		Optional<NSP> nspOpt = nspRepository.getLatestByUserId(userId);
		if (nspOpt.isPresent()) {
			nsp.setNo(nspOpt.get().getNo() + 1);
		} else {
			nsp.setNo(1);
		}
		return NspResp.fromDomain(nspRepository.insert(nsp));
	}

}
