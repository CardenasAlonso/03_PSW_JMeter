package vallegrande.edu.pe.service;

import org.springframework.stereotype.Service;
import vallegrande.edu.pe.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 2500.00));
        products.add(new Product(2, "Mouse", 80.00));
        products.add(new Product(3, "Teclado", 120.00));
        products.add(new Product(4, "Monitor", 850.00));
        products.add(new Product(5, "Audífonos", 150.00));
        return products;
    }

    public String processProduct(String name) {
        if (name == null) {
            return "Producto no válido";
        }
        if (name.isEmpty()) {
            return "Producto vacío";
        }
        return "Producto procesado: " + name;
    }

    public boolean validateProduct(Product product) {
        return product != null
                && product.getName() != null
                && !product.getName().isEmpty()
                && product.getPrice() != null
                && product.getPrice() > 0;
    }
}