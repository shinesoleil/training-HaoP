package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.api.jersey.Routes;
import com.thoughtworks.ketsu.infrastructure.records.Record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Product implements Record{
    private int id;
    private String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Price updatePrice(Map<String, Object> info) {
        return null;
    }

    public List<Price> getPricesHistory() {
        return null;
    }

    public Optional<Price> getPriceById(long id) {
        return null;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> res = new HashMap();
        res.put("id", id);
        res.put("name", name);
        res.put("url", routes.productUrl(this));
        return res;
    }
}
