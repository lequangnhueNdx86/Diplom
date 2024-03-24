package com.dsp.infra.repository.fft;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.infra.entity.fft.FftEntity;
import com.dsp.infra.entity.user.UserEntity;

@Repository
public interface JpaFftRepository extends JpaRepository<FftEntity, String> {
	Optional<FftEntity> findFirstByUserAndTypeOrderByNoDesc(UserEntity userEntity, int type);
	
	Long countByUserAndType(UserEntity user, int type);

}
