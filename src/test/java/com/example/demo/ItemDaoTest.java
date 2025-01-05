package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.dao.ItemDao;
import com.example.demo.domain.Item;
import com.example.demo.domain.Reservation;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ItemDaoTest {

  @Autowired
  private ItemDao itemDaoImpl;

  @Test
  @Transactional
  public void givenItem_whenSave_thenGetOk() {
    List<Reservation> reservations = List.of(
        Reservation.builder()
            .customerId(1L)
            .deliveryDescription("Reservation to first customer")
            .build(),
        Reservation.builder()
            .customerId(2L)
            .deliveryDescription("Reservation to second customer")
            .build(),
        Reservation.builder()
            .customerId(3L)
            .deliveryDescription("Reservation to third customer")
            .build()
    );
    var item = Item.builder()
        .code(123L)
        .active(true)
        .name("bike")
        .reservation(reservations)
        .build();
    var saveResult = itemDaoImpl.saveItem(item);
    assertNotNull(saveResult);
    assertThat(saveResult)
        .usingRecursiveComparison()
        .ignoringFields("id")
        .isEqualTo(item);
    var readResult = itemDaoImpl.getItem(saveResult.getId());
    assertNotNull(readResult);
    assertThat(readResult)
        .usingRecursiveComparison()
        .isEqualTo(saveResult);
  }
}
