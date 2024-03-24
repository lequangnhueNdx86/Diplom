package com.dsp.infra.entity.dft;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.dsp.shared.utils.UuidUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "dft_image_result")
public class DftImageResult {
	@Id
	private String id;
	@ManyToOne(targetEntity = DftEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dft_id")
	@Setter
	private DftEntity dft;
	private String path;
	private Integer index;
	
	public DftImageResult(String path, int index) {
		this.path = path;
		this.index = index;
	}
	
	@PrePersist
	private void ensureId(){
	    this.id = UuidUtils.generateUUID();
	}
}
