package com.dsp.app.finder.dft;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.app.command.dft.DftResp;
import com.dsp.dom.dft.DftRepository;
import com.dsp.shared.auth.AppContexts;
import com.dsp.shared.auth.JwtTokenProvider;

@Service
public class DftFinder {
	@Autowired
	private DftRepository dftRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public List<DftResp> getListDft(int limit) throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		return dftRepository.getAllAndLimit(userId, limit).stream().map(v -> DftResp.fromDomain(v)).collect(Collectors.toList());
	}
	
	public DftResp getDft(String id) {
		Optional<DftResp> dft = dftRepository.getById(id).map(v -> DftResp.fromDomain(v));
		return dft.isPresent() ? dft.get() : null;
	}
	
	public long count() throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		return dftRepository.count(userId);
	}
}
