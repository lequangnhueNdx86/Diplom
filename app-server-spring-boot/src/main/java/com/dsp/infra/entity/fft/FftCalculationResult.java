package com.dsp.infra.entity.fft;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.dsp.dom.fft.CalculationResult;
import com.dsp.shared.utils.UuidUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "fft_calc_result")
public class FftCalculationResult {
	@Id
	private String id;
	@ManyToOne(targetEntity = FftEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "fft_id")
	@Setter
	private FftEntity fft;
	private Integer row;
	private Integer col;
	private String value;
	public FftCalculationResult(Integer row, Integer col, String value) {
		this.row = row;
		this.col = col;
		this.value = value;
	}
	@PrePersist
	private void ensureId(){
	    this.id = UuidUtils.generateUUID();
	}
}
