package org.prgrms.coffee_order_be.common.audit;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Timestamped {

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Timestamped(LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  protected void update() {
    updatedAt = LocalDateTime.now();
  }
}
