package com.dsp.infra.repository.dft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.infra.entity.dft.DftImageResult;

@Repository
public interface JpaDftImageResultRepository extends JpaRepository<DftImageResult, String>{

}
