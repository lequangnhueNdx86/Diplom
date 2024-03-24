package com.dsp.infra.entity.fft;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.dsp.dom.fft.CalculationResult;
import com.dsp.dom.fft.FFT;
import com.dsp.dom.fft.FftType;
import com.dsp.infra.entity.user.UserEntity;
import com.dsp.shared.base.BaseEntity;
import com.dsp.shared.utils.UuidUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "fft_entity")
public class FftEntity extends BaseEntity {
	@Id
	private String id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	@Column(name = "input_arr_as_string")
	private String inputArrayAsString; // split by ,
	private Integer no;
	@OneToMany(mappedBy = "fft", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Setter
	private List<FftCalculationResult> fftCalculationResult;
	private String resultArrayAsString; // split by ,
	private Integer type; // 0: time, 1: freq
	
	public FftEntity(UserEntity user, String inputArrayAsString, Integer no, String resultArrayAsString, int type) {
		super();
		this.user = user;
		this.inputArrayAsString = inputArrayAsString;
		this.no = no;
		this.resultArrayAsString = resultArrayAsString;
		this.type = type;
	}
	
	@PrePersist
	private void ensureId(){
	    this.id = UuidUtils.generateUUID();
	}
	
	public FFT toDomain() {
		return new FFT(	id,
							user.getId(), 
							convertInputToList(), 
							convertToResult(),
							no,
							updateTime.toString(),
							type == 0 ? FftType.TIME : FftType.FREQ);
	}
	
	private List<Double> convertInputToList() {
		String[] arr = this.inputArrayAsString.split(",");
		return Arrays.asList(arr).stream().map(Double::parseDouble).collect(Collectors.toList());
	}
	
	private List<String> convertResultToList() {
		return Arrays.asList(this.resultArrayAsString.split(","));
	}
	
	private CalculationResult convertToResult() {
		String[][] arr = initArr() ;
		for (FftCalculationResult item : fftCalculationResult) {
			arr[item.getRow()][item.getCol()] = item.getValue();
		}
		return new CalculationResult(convertResultToList(), arr);
	}
	
	private String[][] initArr() {
		return new String[][] {
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""}
		};
	}
	
}
