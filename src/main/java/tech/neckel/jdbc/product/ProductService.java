package tech.neckel.jdbc.product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> getProducts();
}
