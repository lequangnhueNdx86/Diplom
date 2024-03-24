package com.dsp.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeQueryDto {
	private long timeFftNum;
	private long freqFftNum;
	private long dftNum;
	private long nspNum;
}
