package com.example.demo.dao;

import com.example.demo.domain.Item;

public interface ItemDao {

  Item getItem(Long id);

  Item saveItem(Item item);
}
