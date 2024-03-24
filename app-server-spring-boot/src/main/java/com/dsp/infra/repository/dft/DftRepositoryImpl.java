package com.dsp.infra.repository.dft;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsp.dom.dft.DFT;
import com.dsp.dom.dft.DftRepository;
import com.dsp.infra.entity.dft.DftEntity;
import com.dsp.infra.entity.dft.DftImageResult;
import com.dsp.infra.repository.user.JpaUserRepository;

@Component
public class DftRepositoryImpl implements DftRepository {
	@Autowired
	private JpaUserRepository jpaUserRepository;
	@Autowired
	private JpaDftRepository jpaDftRepository;
	
	@Autowired
	private JpaDftImageResultRepository jpaDftImageResultRepository;
	
	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public DFT insert(DFT dft) {
		DftEntity dftEntity = jpaDftRepository.saveAndFlush(fromDomain(dft));
		List<DftImageResult> dftImageResults = getListImage(dft);
		dftImageResults.forEach(v -> v.setDft(dftEntity));
		jpaDftImageResultRepository.saveAllAndFlush(dftImageResults);
		dftEntity.setImageList(dftImageResults);
		return dftEntity != null ? dftEntity.toDomain() : null;
	}

	@Override
	public Optional<DFT> getLatestByUserId(String userId) {
		return jpaDftRepository.findFirstByUserOrderByNoDesc(jpaUserRepository.findById(userId).get()).map(v -> v.toDomain());
	}
	
	private DftEntity fromDomain(DFT dft) {
		return new DftEntity(jpaUserRepository.findById(dft.getUserId()).get(), 
				dft.getNo(), 
				dft.getFunc1().getA(),
				dft.getFunc1().getOmega(),
				dft.getFunc2().getA(),
				dft.getFunc2().getOmega(),
				dft.getType().value,
				dft.getAlpha(),
				dft.getBeta());
	}
	
	private List<DftImageResult> getListImage(DFT dft) {
		int i = 1;
		List<DftImageResult> res = new ArrayList<>();
		for (String img : dft.getImageList()) {
			res.add(new DftImageResult(img, i++));
		}
		return res;
	}

	@Override
	public List<DFT> getAllAndLimit(String userId, int limit) {
		return jpaDftRepository.findByUserOrderByNoAsc(userId, limit).stream()
			.map(v -> v.toDomain()).collect(Collectors.toList());
	}

	@Override
	public Optional<DFT> getById(String id) {
		return jpaDftRepository.findById(id).map(v -> v.toDomain());
	}

	@Override
	public Long count(String userId) {
		return jpaDftRepository.countByUser(jpaUserRepository.findById(userId).get());
	}

}
