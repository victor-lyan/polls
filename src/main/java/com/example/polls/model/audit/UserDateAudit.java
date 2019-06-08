package com.example.polls.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@JsonIgnoreProperties(
    value = {"createdBy", "updatedBy"},
    allowGetters = true
)
@Getter
@Setter
public abstract class UserDateAudit extends DateAudit {
    
    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;
    
    @LastModifiedBy
    private Long updatedBy;
}
