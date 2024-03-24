package com.dsp.dom.fft;

import java.util.Optional;

public interface FftRepository {
	FFT insert(FFT fft);
	Optional<FFT> getLatestByUserIdAndType(String userId, int type);
	Long count(String userId, int type);

}
