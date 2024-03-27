package com.eazybytes.eazyschool.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	// The reason why we create another class called "BaseEntity" is because we want all these 4 attributes in all the model classes 
	// included Contact.java , Holiday.java
	// so instead of declaring these 4 attributes for each model class, we could just extend the class
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime updatedAt;
	@LastModifiedBy
	@Column(insertable = false)
	private String updatedBy;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getUpdateAt() {
		return updatedAt;
	}
	public void setUpdateAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUpdateBy() {
		return updatedBy;
	}
	public void setUpdateBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
