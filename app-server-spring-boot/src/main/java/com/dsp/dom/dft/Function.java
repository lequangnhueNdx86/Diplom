package com.dsp.dom.dft;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Function {
	private Double A;
	private Double omega;
	
	public String toString() {
		return "x = " + A + " * cos(" + omega + " * t)";
	}
}
