package com.dsp.infra.repository.fft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.infra.entity.fft.FftCalculationResult;

@Repository
public interface JpaFftCalculationResult extends JpaRepository<FftCalculationResult, String> {

}
