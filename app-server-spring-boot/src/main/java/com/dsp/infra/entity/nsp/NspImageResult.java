package com.dsp.infra.entity.nsp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.dsp.infra.entity.dft.DftEntity;
import com.dsp.infra.entity.user.UserEntity;
import com.dsp.shared.utils.UuidUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "nsp_image_result")
public class NspImageResult {
	@Id
	private String id;
	@ManyToOne(targetEntity = NspEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "nsp_id")
	@Setter
	private NspEntity nsp;
	private String path;
	private Integer index;
	
	public NspImageResult(String path, Integer index) {
		super();
		this.path = path;
		this.index = index;
	}
	
	@PrePersist
	private void ensureId(){
	    this.id = UuidUtils.generateUUID();
	}

}
