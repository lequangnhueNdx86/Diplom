package com.dsp.dom.dft;

import java.util.List;
import java.util.Optional;

public interface DftRepository {
	DFT insert(DFT dft);
	Optional<DFT> getLatestByUserId(String userId);
	List<DFT> getAllAndLimit(String userId, int limit);
	Optional<DFT> getById(String id);
	Long count(String userId);
}
