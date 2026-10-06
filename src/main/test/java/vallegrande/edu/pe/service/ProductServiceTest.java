package main.test.java.vallegrande.edu.pe.service;

import org.junit.jupiter.api.Test;
import vallegrande.edu.pe.model.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private final ProductService productService = new ProductService();

    @Test
    void testGetProductsNotEmpty() {
        assertFalse(productService.getProducts().isEmpty());
    }

    @Test
    void testValidateProductValid() {
        Product p = new Product(1, "Laptop", 2000.0);
        assertTrue(productService.validateProduct(p));
    }

    @Test
    void testValidateProductInvalid() {
        Product p = new Product(2, "", -10.0);
        assertFalse(productService.validateProduct(p));
    }
}