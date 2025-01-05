package com.example.demo.dao;

import com.example.demo.domain.Item;
import com.example.demo.domain.Reservation;
import com.example.demo.jpa.ItemJpa;
import com.example.demo.jpa.ReservationJpa;
import com.example.demo.jpa.ReservationJpa.ReservationJpaCompositeKey;
import com.example.demo.repository.ItemRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemDaoImpl implements ItemDao {

  private final ItemRepository itemRepository;

  private Item fromJpa(ItemJpa jpa) {
    return Item.builder()
        .id(jpa.getId())
        .code(jpa.getCode())
        .name(jpa.getName())
        .active(jpa.getActive())
        .reservation(createReservations(jpa.getReservationJpa()))
        .build();
  }

  private Collection<Reservation> createReservations(Collection<ReservationJpa> reservationJpas) {
    return reservationJpas.stream().map(reservationJpa ->
        Reservation.builder()
            .customerId(reservationJpa.getId().getCustomerId())
            .deliveryDescription(reservationJpa.getDeliveryDescription())
            .build()
    ).toList();
  }


  @Override
  public Item getItem(Long id) {
    ItemJpa resultingJpa = itemRepository.getReferenceById(id);
    return fromJpa(resultingJpa);
  }

  @Override
  public Item saveItem(Item item) {
    ItemJpa jpaToSave = toJpa(item);
    ItemJpa resultingJpa = itemRepository.save(jpaToSave);
    return fromJpa(resultingJpa);
  }

  private static ItemJpa toJpa(Item item) {
    Long itemId = item.getId();

    ItemJpa itemJpa = ItemJpa.builder()
        .id(itemId)
        .code(item.getCode())
        .name(item.getName())
        .active(item.getActive())
        .build();

    Collection<ReservationJpa> reservations = item.getReservation().stream()
        .map(reservation -> ReservationJpa.builder()
            .id(ReservationJpaCompositeKey.of(itemId, reservation.getCustomerId()))
            .item(itemJpa)
            .deliveryDescription(reservation.getDeliveryDescription())
            .build()
        ).toList();

    itemJpa.setReservationJpa(reservations);

    return itemJpa;
  }
}