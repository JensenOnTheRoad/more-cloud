package com.jds.mc.application_api.model.res;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author senreysong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
  private Integer page;
  private Integer size;
  private Integer total;
  private List<T> list;

  public static <T> PageResult<T> of(
      Integer current, Integer size, Integer total, List<T> collect) {
      return new PageResult<>(current, size, total, collect);
  }

  public static <T> PageResult<T> empty() {
    return new PageResult<>(0, 0, 0, Collections.emptyList());
  }
}
