package com.dsp.ac.dft;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DftCommand implements Serializable {
	private Double a1;
	private Double om1;
	private Double a2;
	private Double om2;
	private Integer type;
}
