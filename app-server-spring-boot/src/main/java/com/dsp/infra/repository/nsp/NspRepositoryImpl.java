package com.dsp.infra.repository.nsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.dom.nsp.NSP;
import com.dsp.dom.nsp.NspRepository;
import com.dsp.infra.entity.nsp.NspEntity;
import com.dsp.infra.entity.nsp.NspImageResult;
import com.dsp.infra.repository.user.JpaUserRepository;

@Service
public class NspRepositoryImpl implements NspRepository {
	@Autowired
	private JpaUserRepository jpaUserRepository;
	
	@Autowired
	private JpaNspRepository jpaNspRepository;
	
	@Autowired
	private JpaNspImageResultRepository jpaNspImageResultRepository;

	@Override
	public NSP insert(NSP nsp) {
		NspEntity nspEnttiy = jpaNspRepository.saveAndFlush(fromDomain(nsp));
		List<NspImageResult> nspImageResults = getListImage(nsp);
		nspImageResults.forEach(v -> v.setNsp(nspEnttiy));
		jpaNspImageResultRepository.saveAllAndFlush(nspImageResults);
		nspEnttiy.setImageList(nspImageResults);
		return nspEnttiy != null ? nspEnttiy.toDomain() : null;
	}

	@Override
	public Optional<NSP> getLatestByUserId(String userId) {
		return jpaNspRepository.findFirstByUserOrderByNoDesc(jpaUserRepository.findById(userId).get()).map(v -> v.toDomain());
	}

	@Override
	public List<NSP> getAllAndLimit(String userId, int limit) {
		return jpaNspRepository.findByUserOrderByNoAsc(userId, limit).stream()
				.map(v -> v.toDomain()).collect(Collectors.toList());
	}

	@Override
	public Optional<NSP> getById(String id) {
		return jpaNspRepository.findById(id).map(v -> v.toDomain());
	}
	
	private NspEntity fromDomain(NSP nsp) {
		return new NspEntity(jpaUserRepository.findById(nsp.getUserId()).get(),
							nsp.getNo(),
							nsp.getFrequency(),
							nsp.getNoiseFrequency(),
							nsp.getNumberOfSamples(),
							nsp.getSamplingRate());
	}
	
	private List<NspImageResult> getListImage(NSP dft) {
		int i = 1;
		List<NspImageResult> res = new ArrayList<>();
		for (String img : dft.getImageList()) {
			res.add(new NspImageResult(img, i++));
		}
		return res;
	}

	@Override
	public Long count(String userId) {
		return jpaNspRepository.countByUser(jpaUserRepository.findById(userId).get());
	}

}
