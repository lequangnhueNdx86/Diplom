package com.dsp.shared.base;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@FieldNameConstants
public class BaseEntity {
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    protected Timestamp createTime;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = true)
    @LastModifiedDate
    protected Timestamp updateTime;
    
    @PrePersist
    protected void init() {
    	this.createTime = new Timestamp(new Date().getTime());
    	this.updateTime = new Timestamp(new Date().getTime());
    }
    
    @PreUpdate
    protected void update() {
    	this.updateTime = new Timestamp(new Date().getTime());
    }
}

