package com.thoughtworks.ketsu.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    Product create(Map<String, Object> info);

    Optional<Product> findById(long id);

    Optional<Product> findByName(String name);

    List<Product> find();
}
