package com.dsp.infra.repository.dft;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dsp.infra.entity.dft.DftEntity;
import com.dsp.infra.entity.user.UserEntity;

@Repository
public interface JpaDftRepository extends JpaRepository<DftEntity, String> {
	Optional<DftEntity> findFirstByUserOrderByNoDesc(UserEntity user);
	
	@Query(nativeQuery = true, value = "SELECT * FROM dft_entity d WHERE d.user_id = ?1 ORDER BY d.no DESC LIMIT ?2 ;")
	List<DftEntity> findByUserOrderByNoAsc(String userId, int limit);
	
	Long countByUser(UserEntity user);
}
