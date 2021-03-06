package com.thoughtworks.ketsu.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerRepository {
    Customer create(Map<String, Object> info);

    Optional<Customer> findById(long id);

    List<Customer> find();
}
