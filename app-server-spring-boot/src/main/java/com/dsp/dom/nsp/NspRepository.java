package com.dsp.dom.nsp;

import java.util.List;
import java.util.Optional;

public interface NspRepository {
	NSP insert(NSP nsp);
	
	Optional<NSP> getLatestByUserId(String userId);
	
	List<NSP> getAllAndLimit(String userId, int limit);
	
	Optional<NSP> getById(String id);
	
	Long count(String userId);
}
