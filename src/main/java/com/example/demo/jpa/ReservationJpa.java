package com.example.demo.jpa;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "reservation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationJpa {

  @EmbeddedId
  private ReservationJpaCompositeKey id;

  @MapsId("itemId")
  @ManyToOne
  @JoinColumn(name = "item_id", insertable = false, updatable = false)
  private ItemJpa item;

  private String deliveryDescription;

  @AllArgsConstructor(staticName = "of")
  @NoArgsConstructor
  @Embeddable
  @Data
  public static class ReservationJpaCompositeKey implements Serializable {

    private Long itemId;
    private Long customerId;
  }
}

