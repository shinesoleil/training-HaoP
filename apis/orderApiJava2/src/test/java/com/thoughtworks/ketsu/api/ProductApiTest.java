package com.thoughtworks.ketsu.api;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {

    @Test
    public void should_create_product_success() {
        when(productRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(productRepository.create(any())).thenReturn(new Product(1, "firstProduct"));

        Response response = post("/products", TestHelper.productMap("firstProduct"));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toASCIIString().contains("/products/1"), is(true));
    }

    @Test
    public void should_create_product_failure_with_invalid_info() {
        when(productRepository.create(any())).thenReturn(new Product(1, "firstProduct"));

        Map<String, Object> info = TestHelper.productMap("firstProduct");
        info.replace("name", "");

        Response response = post("/products", info);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_create_product_failure_with_existing_info() {
        when(productRepository.findByName(anyString())).thenReturn(Optional.of(new Product(1, "firstProduct")));

        Response response = post("/products", TestHelper.productMap("firstProduct"));

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_find_all_products_failure() {
        when(productRepository.find()).thenReturn(new ArrayList<Product>());

        Response response = get("/products");

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_find_all_products_success() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "firstProduct"));

        when(productRepository.find()).thenReturn(products);

        Response response = get("/products");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(List.class).size(), is(1));
    }

    @Test
    public void should_find_product_by_id_success() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new Product(1, "firstProduct")));

        Response response = get("/products/1");

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(Map.class).get("id"), is(1));
    }

    @Test
    public void should_find_product_by_id_failure_when_not_found() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Response response = get("/products/1");

        assertThat(response.getStatus(), is(404));
    }
}
