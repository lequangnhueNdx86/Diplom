package com.dsp.infra.repository.fft;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsp.dom.fft.FFT;
import com.dsp.dom.fft.FftRepository;
import com.dsp.dom.fft.FftType;
import com.dsp.infra.entity.fft.FftCalculationResult;
import com.dsp.infra.entity.fft.FftEntity;
import com.dsp.infra.repository.user.JpaUserRepository;
import com.dsp.shared.utils.UuidUtils;

@Component
public class FftRepositoryImpl implements FftRepository {
	@Autowired
	private JpaFftRepository jpaFftRepository;
	@Autowired
	private JpaUserRepository jpaUserRepository;
	@Autowired
	private JpaFftCalculationResult jpaFftCalculationResult;

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public FFT insert(FFT fft) {
		FftEntity timeFftEntity = jpaFftRepository.saveAndFlush(fromDomain(fft));
		List<FftCalculationResult> fftCalculationResults = getListCalculationResult(fft);
		fftCalculationResults.forEach(v -> v.setFft(timeFftEntity));
		jpaFftCalculationResult.saveAllAndFlush(fftCalculationResults);
		timeFftEntity.setFftCalculationResult(fftCalculationResults);
		return (timeFftEntity != null) ? timeFftEntity.toDomain() : null;
	}
	
	@Override
	public Optional<FFT> getLatestByUserIdAndType(String userId, int type) {
		return jpaFftRepository.findFirstByUserAndTypeOrderByNoDesc(jpaUserRepository.findById(userId).get(), type).map(v -> v.toDomain());
	}
	
	private FftEntity fromDomain(FFT fft) {
		return new FftEntity(jpaUserRepository.findById(fft.getUserId()).get(),
								String.join(",", fft.getInputValue().stream().map(v -> v.toString()).collect(Collectors.toList())),
								fft.getNo(),
								String.join(",", fft.getResult().getResult()),
								fft.getType().value);
	}
	
	private List<FftCalculationResult> getListCalculationResult(FFT fft) {
		List<FftCalculationResult> calculationResults = new ArrayList<>();
		String[][] result = fft.getResult().getMatrixValue();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				calculationResults.add(new FftCalculationResult(i, j, result[i][j]));
			}
		}
		return calculationResults;
	}

	@Override
	public Long count(String userId, int type) {
		return jpaFftRepository.countByUserAndType(jpaUserRepository.findById(userId).get(),type);
	}
}
