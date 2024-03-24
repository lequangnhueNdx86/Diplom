package com.dsp.infra.repository.nsp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dsp.infra.entity.nsp.NspEntity;
import com.dsp.infra.entity.user.UserEntity;

@Repository
public interface JpaNspRepository extends JpaRepository<NspEntity, String> {
	Optional<NspEntity> findFirstByUserOrderByNoDesc(UserEntity user);
	
	@Query(nativeQuery = true, value = "SELECT * FROM nsp_entity d WHERE d.user_id = ?1 ORDER BY d.no DESC LIMIT ?2 ;")
	List<NspEntity> findByUserOrderByNoAsc(String userId, int limit);
	
	Long countByUser(UserEntity user);
}
