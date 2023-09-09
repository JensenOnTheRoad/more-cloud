package com.jds.mc.infrastucture.db;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

/**
 * @author jensen_deng
 */
@Getter
@Setter
@FieldNameConstants
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false, updatable = false)
  private Long createdBy;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdTime;

  private Long updatedBy;
  private LocalDateTime updatedTime;

  @Column(name = "deleted")
  private Boolean deleted;

  @PrePersist
  public void prePersist() {
    if (createdTime == null) {
      createdTime = LocalDateTime.now();
    }
    if (deleted == null) {
      deleted = false;
    }
    if (null == createdBy) {
      createdBy = 1L;
    }
  }

  @PreUpdate
  public void preUpdate() {
    updatedTime = LocalDateTime.now();
    updatedBy = 1L;
    if (deleted == null) {
      deleted = false;
    }
  }
}
