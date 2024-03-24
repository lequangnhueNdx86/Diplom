package com.dsp.infra.entity.nsp;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.dsp.dom.nsp.NSP;
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
@Table(name = "nsp_entity")
public class NspEntity extends BaseEntity {
	@Id
	private String id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	private Integer no;
	private Integer frequency;
	private Integer noiseFrequency;
	private Integer numberOfSamples;
	private Integer samplingRate;
	@OneToMany(mappedBy = "nsp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Setter
	private List<NspImageResult> imageList;
	
	public NspEntity(UserEntity user, Integer no, Integer frequency, Integer noisyFrequency, Integer numberOfSamples,
			Integer samplingRate) {
		super();
		this.user = user;
		this.no = no;
		this.frequency = frequency;
		this.noiseFrequency = noisyFrequency;
		this.numberOfSamples = numberOfSamples;
		this.samplingRate = samplingRate;
	}
	
	public NSP toDomain() {
		return new NSP(id, no, user.getId(), frequency, noiseFrequency, numberOfSamples, samplingRate, createTime.toString(),
				imageList.stream().sorted((a, b) -> a.getIndex().compareTo(b.getIndex())).map(v -> v.getPath()).collect(Collectors.toList()));
	}
	
	@PrePersist
	private void ensureId(){
	    this.id = UuidUtils.generateUUID();
	}
}
