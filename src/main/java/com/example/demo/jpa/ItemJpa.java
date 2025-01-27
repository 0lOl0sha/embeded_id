package com.example.demo.jpa;

import static jakarta.persistence.CascadeType.ALL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
  @SequenceGenerator(name = "item_id_seq", allocationSize = 1)
  private Long id;

  private Long code;

  private String name;

  private Boolean active;

  @OneToMany(mappedBy = "item", cascade = ALL, orphanRemoval = true)
  private Collection<ReservationJpa> reservationJpa;
}

