package com.dsp.app.finder.nsp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.app.command.nsp.NspResp;
import com.dsp.dom.nsp.NSP;
import com.dsp.dom.nsp.NspRepository;
import com.dsp.shared.auth.AppContexts;
import com.dsp.shared.auth.JwtTokenProvider;
@Service
public class NspFinder {
	@Autowired
	private NspRepository nspRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public List<NspResp> getListNsp(int limit) throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		return nspRepository.getAllAndLimit(userId, limit).stream()
				.map(v -> NspResp.fromDomain(v)).collect(Collectors.toList());
	}
	
	public NspResp getNspById(String id) {
		Optional<NSP> nsp = nspRepository.getById(id);
		return nsp.isPresent() ? NspResp.fromDomain(nsp.get()) : null;
	}
	
	public long count() throws Exception {
		String userId = AppContexts.getUser(jwtTokenProvider);
		return nspRepository.count(userId);
	}
}
