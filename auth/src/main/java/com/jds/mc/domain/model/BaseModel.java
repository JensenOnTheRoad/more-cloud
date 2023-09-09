package com.jds.mc.domain.model;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

/**
 * @author jensen_deng
 */
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldNameConstants
@Generated
public abstract class BaseModel {

  private Long id;

  private Long createdBy;

  private LocalDateTime createdTime;

  private Long updatedBy;
  private LocalDateTime updatedTime;

  @Builder.Default private Boolean deleted = Boolean.FALSE;
}
