package com.dsp.infra.repository.nsp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.infra.entity.nsp.NspImageResult;

@Repository
public interface JpaNspImageResultRepository extends JpaRepository<NspImageResult, String> {

}
