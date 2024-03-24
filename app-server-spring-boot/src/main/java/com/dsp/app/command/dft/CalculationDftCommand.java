package com.dsp.app.command.dft;

import com.dsp.dom.dft.DFT;
import com.dsp.dom.dft.DftType;
import com.dsp.dom.dft.Function;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CalculationDftCommand {
	private Double a1;
	private Double om1;
	private Double a2;
	private Double om2;
	private int type;
	
	public DFT toDomain() {
		return new DFT(type == 0 ? DftType.LOW : DftType.HIGH, new Function(a1, om1), new Function(a2, om2));
	}
}
