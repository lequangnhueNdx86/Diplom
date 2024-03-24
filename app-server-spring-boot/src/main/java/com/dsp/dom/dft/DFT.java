package com.dsp.dom.dft;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class DFT {
	@Setter
	private String id;
	@Setter
	private Integer no;
	private DftType type;
	@Setter
	private String userId;
	private Function func1;
	private Function func2;
	private String createdAt;
	private List<String> imageList;
	private String alpha;
	private String beta;
	
	public DFT(DftType type, Function func1, Function func2) {
		super();
		this.type = type;
		this.func1 = func1;
		this.func2 = func2;
	}
	
	public void calc(Require require) {
		CalcResp resp = require.calc(this.func1, this.func2, this.type);
		this.imageList = resp.getListResult();
		this.alpha = resp.getAlpha();
		this.beta = resp.getBeta();
	}
	
	public static interface Require {
		CalcResp calc(Function func1, Function func2, DftType type);
	}
}
