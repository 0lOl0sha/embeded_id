package com.example.demo.domain;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Item {

  private Long id;
  private Long code;
  private String name;
  private Boolean active;

  private Collection<Reservation> reservation;
}
