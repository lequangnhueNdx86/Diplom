package com.dsp.infra.entity.dft;

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

import com.dsp.dom.dft.DFT;
import com.dsp.dom.dft.DftType;
import com.dsp.dom.dft.Function;
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
@Table(name = "dft_entity")
public class DftEntity extends BaseEntity {
	@Id
	private String id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;
	private Integer no;
	private Double a1;
	private Double omega1;
	private Double a2;
	private Double omega2;
	private Integer type;
	@OneToMany(mappedBy = "dft", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Setter
	private List<DftImageResult> imageList;
	private String alpha;
	private String beta;

	public DftEntity(UserEntity user, Integer no, Double a1, Double omega1, Double a2, Double omega2, Integer type, String alpha, String beta) {
		super();
		this.user = user;
		this.no = no;
		this.a1 = a1;
		this.omega1 = omega1;
		this.a2 = a2;
		this.omega2 = omega2;
		this.type = type;
		this.alpha = alpha;
		this.beta = beta;
	}
	
	public DFT toDomain() {
		return new DFT(id, no, type == 0 ? DftType.LOW : DftType.HIGH, user.getId(), new Function(a1, omega1), new Function(a2, omega2), updateTime.toString(),
				imageList.stream().sorted((a, b) -> a.getIndex().compareTo(b.getIndex())).map(v -> v.getPath()).collect(Collectors.toList()),
				alpha, beta);
		
	}
	
	@PrePersist
	private void ensureId(){
	    this.id = UuidUtils.generateUUID();
	}
}
